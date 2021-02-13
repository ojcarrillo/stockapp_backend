package com.stockapp.stockapp.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stockapp.stockapp.model.Permisos;

@Repository
public interface PermisosRepository extends CrudRepository<Permisos, Integer> {

	@Query(value = 	"SELECT p.* FROM permisos_us as p " 
					+ "join permisos_usuarios pu on pu.idPermisos = p.idPermisos "
					+ "where p.activo = 1 and pu.activo = 1 and p.menu is not null and pu.idUsuarios = :idusuario", nativeQuery = true)
	ArrayList<Permisos> findByIdUsuario(@Param("idusuario") Integer idusuario);
}
