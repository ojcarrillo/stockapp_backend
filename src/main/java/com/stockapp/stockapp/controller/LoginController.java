package com.stockapp.stockapp.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.stockapp.stockapp.model.Usuario;
import com.stockapp.stockapp.repository.UsuarioRestRepository;
import com.stockapp.stockapp.util.JwtUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {

	@Autowired
	UsuarioRestRepository usuarioService;

	@Autowired
	JwtUtil jwtService;
	
	private final String PREFIX = "Bearer ";

	@PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity login(@RequestBody Usuario usuarioBusqueda) {
		Usuario busqueda = usuarioService.findByLoginAndPassword(usuarioBusqueda.getLogin(),
				usuarioBusqueda.getPassword());
		if (Objects.nonNull(busqueda) && Objects.nonNull(busqueda.getId()) && busqueda.getActivo()) {
			String token = jwtService.getJWTToken(busqueda.getLogin());
			LoginResponse response = new LoginResponse(busqueda, token);
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.notFound().build();
	}

	/*
	 * @GetMapping(path = "/validarLogin") public ResponseEntity
	 * validarLogin(@RequestHeader("x-token") String token) {
	 * System.out.println(token); return ResponseEntity.ok(token); }
	 */

	@GetMapping(path = "/validarToken")
	public ResponseEntity validarToken(@RequestHeader("Authorization") String token) {
		String login = jwtService.getUsernameFromToken(token.replace(PREFIX, ""));
		String newToken = jwtService.getJWTToken(login);
		LoginResponse response = new LoginResponse(null, newToken);
		return ResponseEntity.ok(response);
	}
}

class LoginResponse {

	private Usuario usuario;
	private String token;

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

}
