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
@Table(name = "pagofactura")
public class PagoFactura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpago")
	private Integer id;

	@Column(name = "valorpagado", precision = 10, scale = 2)
	private Double valorPagado;

	@Column(name = "valorfacturado", precision = 10, scale = 2)
	private Double valorFacturado;

	@Column(name = "valorcambio", precision = 10, scale = 2)
	private Double valorCambio;

	@ManyToOne
	@JoinColumn(name = "idventasfactura")
	@JsonBackReference(value = "pago")
	private VentaFactura facturaPago;

	@ManyToOne
	@JoinColumn(name = "idarticulos")
	private Articulo articulo;

	public PagoFactura() {
		super();
	}

	public PagoFactura(Integer id, Double valorPagado, Double valorFacturado, Double valorCambio,
			VentaFactura facturaPago) {
		super();
		this.id = id;
		this.valorPagado = valorPagado;
		this.valorFacturado = valorFacturado;
		this.valorCambio = valorCambio;
		this.facturaPago = facturaPago;
	}

	public PagoFactura(Integer id, Double valorPagado, Double valorFacturado, Double valorCambio, Articulo articulo) {
		super();
		this.id = id;
		this.valorPagado = valorPagado;
		this.valorFacturado = valorFacturado;
		this.valorCambio = valorCambio;
		this.articulo = articulo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValorPagado() {
		return valorPagado;
	}

	public void setValorPagado(Double valorPagado) {
		this.valorPagado = valorPagado;
	}

	public Double getValorFacturado() {
		return valorFacturado;
	}

	public void setValorFacturado(Double valorFacturado) {
		this.valorFacturado = valorFacturado;
	}

	public Double getValorCambio() {
		return valorCambio;
	}

	public void setValorCambio(Double valorCambio) {
		this.valorCambio = valorCambio;
	}

	public VentaFactura getFacturaPago() {
		return facturaPago;
	}

	public void setFacturaPago(VentaFactura facturaPago) {
		this.facturaPago = facturaPago;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

}
