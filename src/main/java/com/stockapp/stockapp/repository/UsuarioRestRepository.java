package com.stockapp.stockapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.stockapp.stockapp.model.Usuario;

@RepositoryRestResource(collectionResourceRel = "usuarios", path = "usuarios")
public interface UsuarioRestRepository extends PagingAndSortingRepository<Usuario, Integer>{

	@RestResource(exported = false)
	public Usuario findByLoginAndPassword(String login, String passsword);
}
