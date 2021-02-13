package com.stockapp.stockapp.projections;

import org.springframework.data.rest.core.config.Projection;

import com.stockapp.stockapp.model.Articulo;
import com.stockapp.stockapp.model.Iva;
import com.stockapp.stockapp.model.Presentacion;
import com.stockapp.stockapp.model.TipoArticulo;
import com.stockapp.stockapp.model.Unidad;

@Projection(name = "articuloAll", types = Articulo.class)
public interface ArticuloProjection {

	public Integer getId();	
	public String getNombreGenerico();
	public String getNombreComercial();
	public Double getValorCompra();
	public Double getValorVenta();
	public Integer getConversion();
	public Integer getExistencias();
	public Integer getExistenciasMinimas();
	public Boolean getActivo();
	public Presentacion getPresentacion();
	public Unidad getUnidadcompra();
	public Unidad getUnidadventa();
	public Iva getIva();
	public TipoArticulo getTipoarticulo();
}
