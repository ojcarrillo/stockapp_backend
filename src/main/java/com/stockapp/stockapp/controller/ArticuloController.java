package com.stockapp.stockapp.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stockapp.stockapp.model.Articulo;
import com.stockapp.stockapp.repository.ArticuloRestRepository;
import com.stockapp.stockapp.util.Utils;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ArticuloController {

	@Autowired
	private ArticuloRestRepository articuloService;

	@PostMapping(path = "/guardarArticulo", consumes = "application/json", produces = "application/json")
	public ResponseEntity guardarArticulo(@RequestBody Articulo articulo) {
		Utils.validarJson(articulo);
		
		if(Objects.nonNull(articulo)) {
			Articulo objGuardado = articuloService.save(articulo);
			if(Objects.nonNull(objGuardado.getId())) {
				System.out.println("id:::"+objGuardado.getId());
				return ResponseEntity.ok(objGuardado);
			}
		}
		
		return ResponseEntity.notFound().build();
	}

}
