package com.stockapp.stockapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "datosprograma")
public class DatosPrograma {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "iddatosprograma")
	private Integer id;
	
	@Column(name = "razonsocial")
	private String razonSocial;

	@Column(name = "nit", length = 15)
	private String nit;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "resolucion")
	private String resolucion;
	
	@Column(name = "fecharesolucion")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaResolucion;
	
	@Column(name = "prefijo", length = 10)
	private String prefijo;
	
	@Column(name = "inicionum")
	private Long inicioNumeracion;
	
	@Column(name = "finnum")
	private Long finNumeracion;
	
	@Column(name = "regimen", length = 10)
	private String regimen;
	
	@JsonIgnore
	@Column(name = "fechamod")
	private Date fechaModificacion;

	public DatosPrograma() {
		super();
	}

	public DatosPrograma(Integer id, String razonSocial, String nit, String direccion, String resolucion,
			Date fechaResolucion, String prefijo, Long inicioNumeracion, Long finNumeracion, String regimen,
			Date fechaModificacion) {
		super();
		this.id = id;
		this.razonSocial = razonSocial;
		this.nit = nit;
		this.direccion = direccion;
		this.resolucion = resolucion;
		this.fechaResolucion = fechaResolucion;
		this.prefijo = prefijo;
		this.inicioNumeracion = inicioNumeracion;
		this.finNumeracion = finNumeracion;
		this.regimen = regimen;
		this.fechaModificacion = fechaModificacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	public Date getFechaResolucion() {
		return fechaResolucion;
	}

	public void setFechaResolucion(Date fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}

	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public Long getInicioNumeracion() {
		return inicioNumeracion;
	}

	public void setInicioNumeracion(Long inicioNumeracion) {
		this.inicioNumeracion = inicioNumeracion;
	}

	public Long getFinNumeracion() {
		return finNumeracion;
	}

	public void setFinNumeracion(Long finNumeracion) {
		this.finNumeracion = finNumeracion;
	}

	public String getRegimen() {
		return regimen;
	}

	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	
}
