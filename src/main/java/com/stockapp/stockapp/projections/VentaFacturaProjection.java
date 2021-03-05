package com.stockapp.stockapp.projections;

import java.util.Date;
import java.util.List;

import com.stockapp.stockapp.model.DetalleVentaFactura;
import com.stockapp.stockapp.model.PagoFactura;

public interface VentaFacturaProjection {

	public Integer getId();
	public String getComprador();
	public String getDocumentoComprador();
	public String getNumeroFactura();
	public Double getValorIva();
	public Double getValorFactura();
	public Double getExentoFactura();
	public Double getBaseFactura();
	public Double getDescuentoFactura();
	public Date getFechaExpedicion();
	public List<DetalleVentaFactura> getArticulos();
	public PagoFactura getPago();
	
}
