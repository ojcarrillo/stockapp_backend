package com.stockapp.stockapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "permisos_us")
@Where(clause = "activo = 1")
public class Permisos {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idpermisos")
	private Integer id;

	@Column(name = "opcion")
	private String etiqueta;

	@Column(name = "path")
	private String path;

	@Column(name = "menu")
	private String menu;

	public Permisos() {
		super();
	}

	public Permisos(Integer id, String etiqueta, String path, String menu) {
		super();
		this.id = id;
		this.etiqueta = etiqueta;
		this.path = path;
		this.menu = menu;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	
}
