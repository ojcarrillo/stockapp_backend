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
import com.stockapp.stockapp.model.DetalleFacturaProveedor;
import com.stockapp.stockapp.model.FacturaProveedor;
import com.stockapp.stockapp.repository.ArticuloRestRepository;
import com.stockapp.stockapp.repository.FacturaProveedorRestRepository;
import com.stockapp.stockapp.util.ErrorResponse;
import com.stockapp.stockapp.util.Utils;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FacturaProveedorController {

	@Autowired
	private FacturaProveedorRestRepository facturaProveedorService;
	
	@Autowired
	private ArticuloRestRepository articuloService;

	@PostMapping(path = "/guardarFactura", consumes = "application/json", produces = "application/json")
	public ResponseEntity guardarFactura(@RequestBody FacturaProveedor factura) {
		Utils.validarJson(factura);
		
		if(Objects.nonNull(factura)) {
			Object o = facturaProveedorService.findByProveedorAndNumeroFactura(factura.getProveedor(), factura.getNumeroFactura());
			if(Objects.isNull(o)) {
				Double total = 0D;
				for(DetalleFacturaProveedor det : factura.getArticulos()) {
					total += det.getValorCompra() * det.getCantidad();
				}
				if(!factura.getValorFactura().equals(total)) {
					ErrorResponse error = new ErrorResponse("El valor de la factura no es igual a la suma los artículos detallados", HttpStatus.BAD_REQUEST.toString());
					return ResponseEntity.badRequest().body(error);
				}
				FacturaProveedor objGuardado = facturaProveedorService.save(factura);
				if(Objects.nonNull(objGuardado.getId())) {
					System.out.println("id:::"+objGuardado.getId());
					Optional<FacturaProveedor> objfinal = actualizarExistencias(objGuardado);
					return ResponseEntity.ok(objfinal.get());
				}
			}else {
				ErrorResponse error = new ErrorResponse("Ya existe un número de factura para el proveedor indicado", HttpStatus.BAD_REQUEST.toString());
				return ResponseEntity.badRequest().body(error);
			}
		}
		
		return ResponseEntity.notFound().build();
	}

	private Optional<FacturaProveedor> actualizarExistencias(FacturaProveedor objGuardado) {
		Optional<FacturaProveedor> objfinal = facturaProveedorService.findById(objGuardado.getId());
		objGuardado.getArticulos().stream().forEach(articulo -> {
			Optional<Articulo> actualizaExistencias = articuloService.findById(articulo.getArticulo().getId());
			if(actualizaExistencias.isPresent()) {
				actualizaExistencias.get().setExistencias(actualizaExistencias.get().getExistencias() + articulo.getCantidad());
				if(!articulo.getBonificacion()) {
					actualizaExistencias.get().setValorVenta(articulo.getValorVenta());
					actualizaExistencias.get().setValorCompra(articulo.getValorCompra());
				}
				articuloService.save(actualizaExistencias.get());
			}
		});
		return objfinal;
	}

}
