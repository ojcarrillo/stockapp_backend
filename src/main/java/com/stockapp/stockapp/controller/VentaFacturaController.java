package com.stockapp.stockapp.controller;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.stockapp.stockapp.model.Articulo;
import com.stockapp.stockapp.model.DetalleVentaFactura;
import com.stockapp.stockapp.model.VentaFactura;
import com.stockapp.stockapp.projections.VentaFacturaEncabezadoProjection;
import com.stockapp.stockapp.repository.ArticuloRestRepository;
import com.stockapp.stockapp.repository.VentaFacturaRestRepository;
import com.stockapp.stockapp.util.ErrorResponse;
import com.stockapp.stockapp.util.Utils;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class VentaFacturaController extends MasterController {

	@Autowired
	private VentaFacturaRestRepository ventaFacturaService;

	@Autowired
	private ArticuloRestRepository articuloService;

	@Autowired
	private PagedResourcesAssembler<VentaFactura> pagedResourcesAssembler;

	@PostMapping(path = "/guardarVenta", consumes = "application/json", produces = "application/json")
	public ResponseEntity guardarVenta(@RequestBody VentaFactura factura) {
		Utils.validarJson(factura);

		if (Objects.nonNull(factura)) {
			Object o = ventaFacturaService.findByNumeroFactura(factura.getNumeroFactura());
			if (Objects.isNull(o)) {
				Double total = 0D;
				for (DetalleVentaFactura det : factura.getArticulos()) {
					total += det.getValorUnitario() * det.getCantidad();
				}
				if (!factura.getValorFactura().equals(total)) {
					ErrorResponse error = new ErrorResponse(
							"El valor de la venta no es igual a la suma los artículos detallados",
							HttpStatus.BAD_REQUEST.toString());
					return ResponseEntity.badRequest().body(error);
				}
				factura.setIdUsuario((Integer) jwtService.getClaimsValueFromToken("userId"));
				VentaFactura objGuardado = ventaFacturaService.save(factura);
				if (Objects.nonNull(objGuardado.getId())) {
					Optional<VentaFactura> objfinal = actualizarExistencias(objGuardado);
					return ResponseEntity.ok(objfinal.get());
				}
			} else {
				ErrorResponse error = new ErrorResponse("Ya existe un número de factura para la venta",
						HttpStatus.BAD_REQUEST.toString());
				return ResponseEntity.badRequest().body(error);
			}
		}

		return ResponseEntity.notFound().build();
	}

	private Optional<VentaFactura> actualizarExistencias(VentaFactura objGuardado) {
		Optional<VentaFactura> objfinal = ventaFacturaService.findById(objGuardado.getId());
		objGuardado.getArticulos().stream().forEach(articulo -> {
			Optional<Articulo> actualizaExistencias = articuloService.findById(articulo.getArticulo().getId());
			if (actualizaExistencias.isPresent()) {
				actualizaExistencias.get()
						.setExistencias(actualizaExistencias.get().getExistencias() - articulo.getCantidad());
				articuloService.save(actualizaExistencias.get());
			}
		});
		return objfinal;
	}	

	@GetMapping(path = "/listarVentas", consumes = "application/json", produces = "application/json")
	public ResponseEntity listarVentasFacturaEncabezado(
			@RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date fechaInicio,
			@RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date fechaFin,
			Pageable pageable) {
		Integer idUsuario = (Integer) jwtService.getClaimsValueFromToken("userId");
		Page results = ventaFacturaService.findVentasFacturaByFecha(fechaInicio, fechaFin, idUsuario, pageable)
				.map(venta -> projectionFactory.createProjection(VentaFacturaEncabezadoProjection.class, venta));
		PagedModel<VentaFactura> collModel = pagedResourcesAssembler.toModel(results);
		return ResponseEntity.ok(collModel);
	}
	
	
	@GetMapping(path = "/totalizarVentas", consumes = "application/json", produces = "application/json")
	public ResponseEntity totalesVentasFactura(
			@RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date fechaInicio,
			@RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date fechaFin) {
		Integer idUsuario = (Integer) jwtService.getClaimsValueFromToken("userId");
				
		Object[] results = (Object[]) ventaFacturaService.totalesVentasFacturaByFecha(fechaInicio, fechaFin, idUsuario);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode result = mapper.createObjectNode();
		if(Objects.nonNull(results)) {			
			result.put("totalBase", results[0].toString());
			result.put("totalExento", results[1].toString());
			result.put("totalIva", results[2].toString());
			result.put("totalFacturas", results[3].toString());
			result.put("cantFacturas", results[4].toString());
		}		
		return ResponseEntity.ok(result.toString());
	}
	
}
