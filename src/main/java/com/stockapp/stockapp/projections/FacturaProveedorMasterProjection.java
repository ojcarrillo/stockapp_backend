package com.stockapp.stockapp.projections;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.stockapp.stockapp.model.FacturaProveedor;
import com.stockapp.stockapp.model.Proveedor;

@Projection(name="master", types = { FacturaProveedor.class })
public interface FacturaProveedorMasterProjection {

	public Integer getId();
	public String getNumeroFactura();
	public Double getValorIva();
	public Double getValorFactura();
	public Date getFechaExpedicion();
	public Date getFechaVencimiento();
	public String getAnotaciones();
	public Proveedor getProveedor();
	
}
