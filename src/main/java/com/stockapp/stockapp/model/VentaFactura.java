package com.stockapp.stockapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ventasfactura")
public class VentaFactura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idventasfactura")
	private Integer id;

	@Column(name = "nombrevende")
	private String comprador;

	@Column(name = "nit")
	private String documentoComprador;

	@Column(name = "numerofactura")
	private String numeroFactura;

	@Column(name = "ivatotfactura", precision = 10, scale = 2)
	private Double valorIva;

	@Column(name = "valortotfactura", precision = 10, scale = 2)
	private Double valorFactura;

	@Column(name = "exentototfactura", precision = 10, scale = 2)
	private Double exentoFactura;

	@Column(name = "basefactura", precision = 10, scale = 2)
	private Double baseFactura;

	@Column(name = "desctotfactura", precision = 10, scale = 2)
	private Double descuentoFactura;

	@Column(name = "fechahoraventa", insertable = false, updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaVenta;
	
	@Column(name = "idvendedor")
	private Integer idUsuario;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "factura", orphanRemoval = true)
	@JsonManagedReference(value = "articulos")
	private List<DetalleVentaFactura> articulos;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "facturaPago", orphanRemoval = true)
	@JsonManagedReference(value = "pago")
	private PagoFactura pago;
	
	public VentaFactura() {
		super();
	}

	public VentaFactura(Integer id, String comprador, String documentoComprador, String numeroFactura, Double valorIva,
			Double valorFactura, Double exentoFactura, Double baseFactura, Double descuentoFactura, Date fechaVenta, Integer idUsuario,
			List<DetalleVentaFactura> articulos, PagoFactura pago) {
		super();
		this.id = id;
		this.comprador = comprador;
		this.documentoComprador = documentoComprador;
		this.numeroFactura = numeroFactura;
		this.valorIva = valorIva;
		this.valorFactura = valorFactura;
		this.exentoFactura = exentoFactura;
		this.baseFactura = baseFactura;
		this.descuentoFactura = descuentoFactura;
		this.fechaVenta = fechaVenta;
		this.idUsuario = idUsuario;
		this.articulos = articulos;
		this.pago = pago;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComprador() {
		return comprador;
	}

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

	public String getDocumentoComprador() {
		return documentoComprador;
	}

	public void setDocumentoComprador(String documentoComprador) {
		this.documentoComprador = documentoComprador;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Double getValorIva() {
		return valorIva;
	}

	public void setValorIva(Double valorIva) {
		this.valorIva = valorIva;
	}

	public Double getValorFactura() {
		return valorFactura;
	}

	public void setValorFactura(Double valorFactura) {
		this.valorFactura = valorFactura;
	}

	public Double getExentoFactura() {
		return exentoFactura;
	}

	public void setExentoFactura(Double exentoFactura) {
		this.exentoFactura = exentoFactura;
	}

	public Double getBaseFactura() {
		return baseFactura;
	}

	public void setBaseFactura(Double baseFactura) {
		this.baseFactura = baseFactura;
	}

	public Double getDescuentoFactura() {
		return descuentoFactura;
	}

	public void setDescuentoFactura(Double descuentoFactura) {
		this.descuentoFactura = descuentoFactura;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<DetalleVentaFactura> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<DetalleVentaFactura> articulos) {
		this.articulos = articulos;
	}

	public PagoFactura getPago() {
		return pago;
	}

	public void setPago(PagoFactura pago) {
		this.pago = pago;
	}

}
