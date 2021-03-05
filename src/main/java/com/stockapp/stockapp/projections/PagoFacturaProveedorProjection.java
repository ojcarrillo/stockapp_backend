package com.stockapp.stockapp.projections;

import java.util.Date;

import com.stockapp.stockapp.model.FacturaProveedor;

public interface PagoFacturaProveedorProjection {

	public Integer getId();
	public Date getFechaPago();
	public String getMetodoPago();
	public Double getValorPago();
	public String getBanco();
	public String getCheque();
	public Double getDescuento();
	public FacturaProveedor getFactura();
}
