package com.stockapp.stockapp.projections;

import java.util.Date;
import java.util.List;

import com.stockapp.stockapp.model.DetalleFacturaProveedor;
import com.stockapp.stockapp.model.Proveedor;

public interface FacturaProveedorProjection {

	public Integer getId();
	public String getNumeroFactura();
	public Double getValorIva();
	public Double getValorFactura();
	public Date getFechaExpedicion();
	public Date getFechaVencimiento();
	public String getAnotaciones();
	public Proveedor getProveedor();
	public List<DetalleFacturaProveedor> getArticulos();
	
}
