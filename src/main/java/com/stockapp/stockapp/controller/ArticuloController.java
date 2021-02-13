package com.stockapp.stockapp.controller;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockapp.stockapp.model.Articulo;
import com.stockapp.stockapp.repository.ArticuloRestRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ArticuloController {

	@Autowired
	private ArticuloRestRepository articuloService;

	@PostMapping(path = "/guardarArticulo", consumes = "application/json", produces = "application/json")
	public ResponseEntity guardarArticulo(@RequestBody Articulo articulo) {
		validarJson(articulo);
		
		if(Objects.nonNull(articulo)) {
			Articulo objGuardado = articuloService.save(articulo);
			if(Objects.nonNull(objGuardado.getId())) {
				System.out.println("id:::"+objGuardado.getId());
				return ResponseEntity.ok(objGuardado);
			}
		}
		
		return ResponseEntity.notFound().build();
	}
	
	

	private void validarJson(Articulo articulo) {
		ObjectMapper Obj = new ObjectMapper();

		try {

			// get Oraganisation object as a json string
			String jsonStr = Obj.writeValueAsString(articulo);

			// Displaying JSON String
			System.out.println(jsonStr);
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
