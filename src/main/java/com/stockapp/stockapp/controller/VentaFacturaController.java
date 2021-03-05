package com.stockapp.stockapp.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stockapp.stockapp.model.Articulo;
import com.stockapp.stockapp.model.DetalleVentaFactura;
import com.stockapp.stockapp.model.VentaFactura;
import com.stockapp.stockapp.repository.ArticuloRestRepository;
import com.stockapp.stockapp.repository.VentaFacturaRestRepository;
import com.stockapp.stockapp.util.ErrorResponse;
import com.stockapp.stockapp.util.Utils;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class VentaFacturaController {

	@Autowired
	private VentaFacturaRestRepository ventaFacturaService;
	
	@Autowired
	private ArticuloRestRepository articuloService;

	@PostMapping(path = "/guardarVenta", consumes = "application/json", produces = "application/json")
	public ResponseEntity guardarVenta(@RequestBody VentaFactura factura) {
		Utils.validarJson(factura);
		
		if(Objects.nonNull(factura)) {
			Object o = ventaFacturaService.findByNumeroFactura(factura.getNumeroFactura());
			if(Objects.isNull(o)) {
				Double total = 0D;
				for(DetalleVentaFactura det : factura.getArticulos()) {
					total += det.getValorUnitario() * det.getCantidad();
				}
				if(!factura.getValorFactura().equals(total)) {
					ErrorResponse error = new ErrorResponse("El valor de la venta no es igual a la suma los artículos detallados", HttpStatus.BAD_REQUEST.toString());
					return ResponseEntity.badRequest().body(error);
				}
				VentaFactura objGuardado = ventaFacturaService.save(factura);
				if(Objects.nonNull(objGuardado.getId())) {
					System.out.println("id:::"+objGuardado.getId());
					Optional<VentaFactura> objfinal = actualizarExistencias(objGuardado);
					return ResponseEntity.ok(objfinal.get());
				}
			}else {
				ErrorResponse error = new ErrorResponse("Ya existe un número de factura para la venta", HttpStatus.BAD_REQUEST.toString());
				return ResponseEntity.badRequest().body(error);
			}
		}
		
		return ResponseEntity.notFound().build();
	}

	private Optional<VentaFactura> actualizarExistencias(VentaFactura objGuardado) {
		Optional<VentaFactura> objfinal = ventaFacturaService.findById(objGuardado.getId());
		objGuardado.getArticulos().stream().forEach(articulo -> {
			Optional<Articulo> actualizaExistencias = articuloService.findById(articulo.getArticulo().getId());
			if(actualizaExistencias.isPresent()) {
				actualizaExistencias.get().setExistencias(actualizaExistencias.get().getExistencias() - articulo.getCantidad());
				articuloService.save(actualizaExistencias.get());
			}
		});
		return objfinal;
	}

}
