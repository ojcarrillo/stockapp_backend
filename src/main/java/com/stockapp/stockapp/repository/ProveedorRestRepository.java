package com.stockapp.stockapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.stockapp.stockapp.model.Proveedor;

@RepositoryRestResource(collectionResourceRel = "proveedores", path = "proveedores")
public interface ProveedorRestRepository extends PagingAndSortingRepository<Proveedor, Integer> {

	@RestResource(path = "filtrarProveedores", rel="proveedores")
	@Query(value="SELECT * FROM proveedores where lower(nombreproveedor) like concat_ws('','%',:filtro,'%') or nitproveedor like concat_ws('',:filtro,'%') ", nativeQuery = true)
	public Page<Proveedor> findByNombreOrNit(@Param("filtro") String filtro, Pageable pageable);
}
