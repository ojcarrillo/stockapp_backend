package com.stockapp.stockapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "articulos")
public class Articulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idarticulos")
	private Integer id;

	@Column(name = "nombregenerico")
	private String nombreGenerico;

	@Column(name = "nombrecomercial")
	private String nombreComercial;

	@Column(name = "valorcompra", precision = 10, scale = 2)
	private Double valorCompra;

	@Column(name = "valorventa", precision = 10, scale = 2)
	private Double valorVenta;

	@Column(name = "conversion")
	private Integer conversion;

	@Column(name = "referencia")
	private String referencia;

	@Column(name = "existencias")
	private Integer existencias;

	@Column(name = "existenciasminimas")
	private Integer existenciasMinimas;

	@Column(name = "activo")
	private Boolean activo;

	@ManyToOne
	@JoinColumn(name = "presentacion", nullable = false)
	private Presentacion presentacion;

	@ManyToOne
	@JoinColumn(name = "unidadcompra", nullable = false)
	private Unidad unidadcompra;

	@ManyToOne
	@JoinColumn(name = "unidadventa", nullable = false)
	private Unidad unidadventa;

	@ManyToOne
	@JoinColumn(name = "iva", nullable = false)
	private Iva iva;

	@ManyToOne
	@JoinColumn(name = "tipoarticulo", nullable = false)
	private TipoArticulo tipoarticulo;

	public Articulo() {
		super();
	}

	public Articulo(Integer id, String nombreGenerico, String nombreComercial, Double valorCompra, Double valorVenta,
			Integer conversion, String referencia, Integer existencias, Integer existenciasMinimas, Boolean activo,
			Presentacion presentacion, Unidad unidadcompra, Unidad unidadventa, Iva iva, TipoArticulo tipoarticulo) {
		super();
		this.id = id;
		this.nombreGenerico = nombreGenerico;
		this.nombreComercial = nombreComercial;
		this.valorCompra = valorCompra;
		this.valorVenta = valorVenta;
		this.conversion = conversion;
		this.referencia = referencia;
		this.existencias = existencias;
		this.existenciasMinimas = existenciasMinimas;
		this.activo = activo;
		this.presentacion = presentacion;
		this.unidadcompra = unidadcompra;
		this.unidadventa = unidadventa;
		this.iva = iva;
		this.tipoarticulo = tipoarticulo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreGenerico() {
		return nombreGenerico;
	}

	public void setNombreGenerico(String nombreGenerico) {
		this.nombreGenerico = nombreGenerico;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public Double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(Double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Double getValorVenta() {
		return valorVenta;
	}

	public void setValorVenta(Double valorVenta) {
		this.valorVenta = valorVenta;
	}

	public Integer getConversion() {
		return conversion;
	}

	public void setConversion(Integer conversion) {
		this.conversion = conversion;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Integer getExistencias() {
		return existencias;
	}

	public void setExistencias(Integer existencias) {
		this.existencias = existencias;
	}

	public Integer getExistenciasMinimas() {
		return existenciasMinimas;
	}

	public void setExistenciasMinimas(Integer existenciasMinimas) {
		this.existenciasMinimas = existenciasMinimas;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Presentacion getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(Presentacion presentacion) {
		this.presentacion = presentacion;
	}

	public Unidad getUnidadcompra() {
		return unidadcompra;
	}

	public void setUnidadcompra(Unidad unidadcompra) {
		this.unidadcompra = unidadcompra;
	}

	public Unidad getUnidadventa() {
		return unidadventa;
	}

	public void setUnidadventa(Unidad unidadventa) {
		this.unidadventa = unidadventa;
	}

	public Iva getIva() {
		return iva;
	}

	public void setIva(Iva iva) {
		this.iva = iva;
	}

	public TipoArticulo getTipoarticulo() {
		return tipoarticulo;
	}

	public void setTipoarticulo(TipoArticulo tipoarticulo) {
		this.tipoarticulo = tipoarticulo;
	}

}
