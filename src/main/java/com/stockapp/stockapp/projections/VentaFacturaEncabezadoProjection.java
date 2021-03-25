package com.stockapp.stockapp.projections;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.stockapp.stockapp.model.PagoFactura;
import com.stockapp.stockapp.model.VentaFactura;

@Projection(name="encabezado", types = { VentaFactura.class}) 
public interface VentaFacturaEncabezadoProjection {

	public Integer getId();
	public String getComprador();
	public String getDocumentoComprador();
	public String getNumeroFactura();
	public Double getValorIva();
	public Double getValorFactura();
	public Double getExentoFactura();
	public Double getBaseFactura();
	public Double getDescuentoFactura();
	public Date getFechaVenta();
	public PagoFactura getPago();
	
}
