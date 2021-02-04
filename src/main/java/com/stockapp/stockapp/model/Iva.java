package com.stockapp.stockapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "iva")
public class Iva {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idiva")
	private Integer id;

	@Column(name = "tipo")
	private String nombre;

	@Column(name = "valor", precision = 10, scale = 2)
	private Double porcentaje;

	public Iva() {
		super();
	}

	public Iva(Integer id, String nombre, Double porcentaje) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.porcentaje = porcentaje;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}



}
