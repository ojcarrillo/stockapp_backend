package com.stockapp.stockapp.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.stockapp.stockapp.model.Permisos;
import com.stockapp.stockapp.model.Usuario;

public class LoginResponse {

	private Usuario usuario;
	private String token;
	private HashMap<String, ArrayList<Permisos>> menu;

	public LoginResponse() {
		super();
	}

	public LoginResponse(Usuario usuario, String token) {
		super();
		this.usuario = usuario;
		this.token = token;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public HashMap<String, ArrayList<Permisos>> getMenu() {
		return menu;
	}

	public void setMenu(HashMap<String, ArrayList<Permisos>> menu) {
		this.menu = menu;
	}

	
}
