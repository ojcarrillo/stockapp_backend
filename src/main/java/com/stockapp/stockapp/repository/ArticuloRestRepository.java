package com.stockapp.stockapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.stockapp.stockapp.model.Articulo;
import com.stockapp.stockapp.projections.ArticuloProjection;

@RepositoryRestResource(collectionResourceRel = "articulos", path = "articulos", excerptProjection = ArticuloProjection.class)
public interface ArticuloRestRepository extends PagingAndSortingRepository<Articulo, Integer> {

	@Override
    @RestResource(exported=false)
	Articulo save(Articulo articulo);
	
	@RestResource(path = "filtrarArticulos", rel="articulos")
	@Query(value="SELECT * FROM articulos where lower(nombregenerico) like concat_ws('','%',:filtro,'%') or lower(nombrecomercial) like concat_ws('','%',:filtro,'%') ", nativeQuery = true)
	public Page<Articulo> findByNombreArticulo(@Param("filtro") String filtro, Pageable pageable);
	
	
	@RestResource(path = "findById", rel="articulos")
	@Query(value="SELECT * FROM articulos where idarticulos = :id", nativeQuery = true)
	public Page<Articulo> findByIdArticulos(@Param("id") String id, Pageable pageable);
	
}
