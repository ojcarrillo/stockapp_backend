package com.stockapp.stockapp.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockapp.stockapp.util.AttributeEncryptor;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idusuarios")
	private Integer id;

	@Column(name = "nombrecompleto")
	private String nombre;

	@Column(name = "cedula")
	private String documento;

	@Column(name = "telfijo")
	private String telefono;

	@Column(name = "telcelular")
	private String celular;

	@Column(name = "login")
	private String login;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  
	@Convert(converter = AttributeEncryptor.class)
	@Column(name = "passlogin")
	private String password;

	@JsonIgnore
	@Column(name = "perfil")
	private Long perfilId;

	@Column(name = "activo")
	private Boolean activo;

	public Usuario(Integer id, String nombre, String documento, String telefono, String celular, String login,
			String password, Long perfilId, Boolean activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.documento = documento;
		this.telefono = telefono;
		this.celular = celular;
		this.login = login;
		this.password = password;
		this.perfilId = perfilId;
		this.activo = activo;
	}

	public Usuario() {
		super();
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

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getPerfilId() {
		return perfilId;
	}

	public void setPerfilId(Long perfilId) {
		this.perfilId = perfilId;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}
