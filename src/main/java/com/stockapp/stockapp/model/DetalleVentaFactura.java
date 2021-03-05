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
@Table(name = "detventafactura")
public class DetalleVentaFactura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "cantidad")
	private Integer cantidad;

	@Column(name = "valorunitario", precision = 10, scale = 2)
	private Double valorUnitario;

	@Column(name = "valoriva", precision = 10, scale = 2)
	private Double valorIva;

	@Column(name = "valordesc", precision = 10, scale = 2)
	private Double valorDescuento;

	@Column(name = "valorexentoarticulo", precision = 10, scale = 2)
	private Double valorExentoArticulo;

	@Column(name = "valortotarticulo", precision = 10, scale = 2)
	private Double valorArticulo;

	@ManyToOne
	@JoinColumn(name = "idarticulos", nullable = false)
	private Articulo articulo;

	@ManyToOne
	@JoinColumn(name = "idventasfactura")
	@JsonBackReference(value = "articulos")
	private VentaFactura factura;

	public DetalleVentaFactura() {
		super();
	}

	public DetalleVentaFactura(Integer id, Integer cantidad, Double valorUnitario, Double valorIva,
			Double valorDescuento, Double valorExentoArticulo, Double valorArticulo, Articulo articulo,
			VentaFactura factura) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.valorUnitario = valorUnitario;
		this.valorIva = valorIva;
		this.valorDescuento = valorDescuento;
		this.valorExentoArticulo = valorExentoArticulo;
		this.valorArticulo = valorArticulo;
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

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Double getValorIva() {
		return valorIva;
	}

	public void setValorIva(Double valorIva) {
		this.valorIva = valorIva;
	}

	public Double getValorDescuento() {
		return valorDescuento;
	}

	public void setValorDescuento(Double valorDescuento) {
		this.valorDescuento = valorDescuento;
	}

	public Double getValorExentoArticulo() {
		return valorExentoArticulo;
	}

	public void setValorExentoArticulo(Double valorExentoArticulo) {
		this.valorExentoArticulo = valorExentoArticulo;
	}

	public Double getValorArticulo() {
		return valorArticulo;
	}

	public void setValorArticulo(Double valorArticulo) {
		this.valorArticulo = valorArticulo;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public VentaFactura getFactura() {
		return factura;
	}

	public void setFactura(VentaFactura factura) {
		this.factura = factura;
	}

}
