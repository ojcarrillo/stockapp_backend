package com.stockapp.stockapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.stockapp.stockapp.model.Permisos;
import com.stockapp.stockapp.model.Usuario;
import com.stockapp.stockapp.repository.PermisosRepository;
import com.stockapp.stockapp.repository.UsuarioRestRepository;
import com.stockapp.stockapp.util.JwtUtil;
import com.stockapp.stockapp.util.LoginResponse;
import com.stockapp.stockapp.util.Utils;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {

	@Autowired
	JwtUtil jwtService;

	@Autowired
	UsuarioRestRepository usuarioService;

	@Autowired
	PermisosRepository permisosService;
	
	@PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity login(@RequestBody Usuario usuarioBusqueda) {
		Usuario busqueda = usuarioService.findByLoginAndPassword(usuarioBusqueda.getLogin(),
				usuarioBusqueda.getPassword());
		if (Objects.nonNull(busqueda) && Objects.nonNull(busqueda.getId()) && busqueda.getActivo()) {
			String token = jwtService.getJWTToken(busqueda.getLogin(), busqueda.getId());
			LoginResponse response = new LoginResponse(busqueda, token);
			response.setMenu(armarEstructuraMenu(busqueda));
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.notFound().build();
	}

	private HashMap<String, ArrayList<Permisos>> armarEstructuraMenu(Usuario busqueda) {
		HashMap<String, ArrayList<Permisos>> hashMenu = new HashMap<String, ArrayList<Permisos>>();
		for (Permisos obj : permisosService.findByIdUsuario(busqueda.getId())) {
			if(!hashMenu.containsKey(obj.getMenu())) {
				hashMenu.put(obj.getMenu(), new ArrayList<Permisos>());					
			}
			hashMenu.get(obj.getMenu()).add(obj);
		}
		return hashMenu;
	}

	@GetMapping(path = "/validarToken")
	public ResponseEntity validarToken(@RequestHeader("Authorization") String token) {
		String login = jwtService.getUsernameFromToken(token.replace(Utils.PREFIX, ""));
		Integer userId = jwtService.getUserIdFromToken(token.replace(Utils.PREFIX, ""));
		String newToken = jwtService.getJWTToken(login, userId);
		LoginResponse response = new LoginResponse(null, newToken);
		return ResponseEntity.ok(response);
	}
}

