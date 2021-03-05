package com.stockapp.stockapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "detfacturaproveedor")
public class DetalleFacturaProveedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "cantidad")
	private Integer cantidad;
	
	@Column(name = "valorcompra", precision = 10, scale = 2)
	private Double valorCompra;

	@Column(name = "valorventa", precision = 10, scale = 2)
	private Double valorVenta;
	
	@Column(name = "bonificacion")
	private Boolean bonificacion;
	
	@ManyToOne
	@JoinColumn(name = "idarticulos", nullable = false)
	private Articulo articulo;
	
	@ManyToOne
	@JoinColumn(name = "idfacturasproveedor")
	@JsonBackReference
	private FacturaProveedor factura;

	public DetalleFacturaProveedor() {
		super();
	}

	public DetalleFacturaProveedor(Integer id, Integer cantidad, Double valorCompra, Double valorVenta,
			Boolean bonificacion, Articulo articulo, FacturaProveedor factura) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.valorCompra = valorCompra;
		this.valorVenta = valorVenta;
		this.bonificacion = bonificacion;
		this.articulo = articulo;
		this.factura = factura;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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

	public Boolean getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(Boolean bonificacion) {
		this.bonificacion = bonificacion;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public FacturaProveedor getFactura() {
		return factura;
	}

	public void setFactura(FacturaProveedor factura) {
		this.factura = factura;
	}
	
	
}
