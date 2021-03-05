package com.stockapp.stockapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pagofactproveedor")
public class PagoFacturaProveedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpagofactproveedor")
	private Integer id;

	@Column(name = "fechacancelacion")
	private Date fechaPago;

	@Column(name = "metpago")
	private String metodoPago;

	@Column(name = "valorpago")
	private Double valorPago;

	@Column(name = "banco")
	private String banco;

	@Column(name = "cheque")
	private String cheque;

	@Column(name = "descuento")
	private Double descuento;

	@ManyToOne
	@JoinColumn(name = "idfacturasproveedor", nullable = false)
	private FacturaProveedor factura;

	public PagoFacturaProveedor() {
		super();
	}

	public PagoFacturaProveedor(Integer id, Date fechaPago, String metodoPago, Double valorPago, String banco,
			String cheque, Double descuento, FacturaProveedor factura) {
		super();
		this.id = id;
		this.fechaPago = fechaPago;
		this.metodoPago = metodoPago;
		this.valorPago = valorPago;
		this.banco = banco;
		this.cheque = cheque;
		this.descuento = descuento;
		this.factura = factura;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCheque() {
		return cheque;
	}

	public void setCheque(String cheque) {
		this.cheque = cheque;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public FacturaProveedor getFactura() {
		return factura;
	}

	public void setFactura(FacturaProveedor factura) {
		this.factura = factura;
	}

}
