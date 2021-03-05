package com.stockapp.stockapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "facturasproveedor")
public class FacturaProveedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idfacturasproveedor")
	private Integer id;

	@Column(name = "facturanum")
	private String numeroFactura;

	@Column(name = "ivafactura", precision = 10, scale = 2)
	private Double valorIva;

	@Column(name = "valorfactura", precision = 10, scale = 2)
	private Double valorFactura;

	@Column(name = "fechafact")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaExpedicion;

	@Column(name = "fechavencimiento")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaVencimiento;

	@Column(name = "anotacion")
	private String anotaciones;

	@Column(name = "pago")
	private String estado;

	@ManyToOne
	@JoinColumn(name = "idproveedores", nullable = false)
	private Proveedor proveedor;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "factura", orphanRemoval = true)
	@JsonManagedReference
	private List<DetalleFacturaProveedor> articulos;

	public FacturaProveedor() {
		super();
	}

	public FacturaProveedor(Integer id, String numeroFactura, Double valorIva, Double valorFactura,
			Date fechaExpedicion, Date fechaVencimiento, String anotaciones, String estado, Proveedor proveedor,
			List<DetalleFacturaProveedor> articulos) {
		super();
		this.id = id;
		this.numeroFactura = numeroFactura;
		this.valorIva = valorIva;
		this.valorFactura = valorFactura;
		this.fechaExpedicion = fechaExpedicion;
		this.fechaVencimiento = fechaVencimiento;
		this.anotaciones = anotaciones;
		this.estado = estado;
		this.proveedor = proveedor;
		this.articulos = articulos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(Date fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getAnotaciones() {
		return anotaciones;
	}

	public void setAnotaciones(String anotaciones) {
		this.anotaciones = anotaciones;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public List<DetalleFacturaProveedor> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<DetalleFacturaProveedor> articulos) {
		this.articulos = articulos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@PrePersist
	private void prePersist() {
		for (DetalleFacturaProveedor am : articulos) {
			am.setFactura(this);
		}
	}
}
