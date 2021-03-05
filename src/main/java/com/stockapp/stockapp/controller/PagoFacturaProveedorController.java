package com.stockapp.stockapp.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stockapp.stockapp.model.FacturaProveedor;
import com.stockapp.stockapp.model.PagoFacturaProveedor;
import com.stockapp.stockapp.repository.FacturaProveedorRestRepository;
import com.stockapp.stockapp.repository.PagoFacturaProveedorRestRepositoy;
import com.stockapp.stockapp.util.ErrorResponse;
import com.stockapp.stockapp.util.Utils;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PagoFacturaProveedorController {

	@Autowired
	private PagoFacturaProveedorRestRepositoy pagoFacturaProveedorService;

	@Autowired
	private FacturaProveedorRestRepository facturaProveedorService;

	@PostMapping(path = "/guardarPagoFacturaProveedor", consumes = "application/json", produces = "application/json")
	public ResponseEntity guardarPagoFacturaProveedor(@RequestBody PagoFacturaProveedor pago) {
		Utils.validarJson(pago);

		if (Objects.nonNull(pago)) {
			Optional<FacturaProveedor> factura = facturaProveedorService.findById(pago.getFactura().getId());

			if (factura.isPresent()) {
				if("pagada".equalsIgnoreCase(factura.get().getEstado())) {
					ErrorResponse error = new ErrorResponse(
							"La factura ya ha sido pagada en su totalidad",
							HttpStatus.BAD_REQUEST.toString());
					return ResponseEntity.badRequest().body(error);
				}
				
				Double saldoAcumulado = pagoFacturaProveedorService.getTotalPagosAcumuladosByFactura(pago.getFactura().getId());
				
				Double saldo = factura.get().getValorFactura() - (Objects.nonNull(saldoAcumulado) ? saldoAcumulado : 0D);
				Double nuevoSaldo = saldo - pago.getValorPago() - pago.getDescuento();
				if (nuevoSaldo < 0) {
					ErrorResponse error = new ErrorResponse(
							"El valor del pago es superior al saldo actual de la factura [saldo: $ " + saldo + "]",
							HttpStatus.BAD_REQUEST.toString());
					return ResponseEntity.badRequest().body(error);
				}
				PagoFacturaProveedor objfinal = pagoFacturaProveedorService.save(pago);
				if(nuevoSaldo.equals(0.0D)) {
					factura.get().setEstado("pagada");
					facturaProveedorService.save(factura.get());
				}
				return ResponseEntity.ok(objfinal);
			}
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(path = "/totalPagoFacturaProveedor/{idproveedor}", consumes = "application/json", produces = "application/json")
	public ResponseEntity totalPagoFacturaProveedor(@PathVariable Integer idproveedor) {
		if(Objects.nonNull(idproveedor)) {
			List<Object[]> results = pagoFacturaProveedorService.getTotalPagosByProveedor(idproveedor);
			return ResponseEntity.ok(results);
		}		
		return ResponseEntity.notFound().build();
	}

}
