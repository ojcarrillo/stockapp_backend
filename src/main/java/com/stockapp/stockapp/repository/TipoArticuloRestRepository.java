package com.stockapp.stockapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stockapp.stockapp.model.TipoArticulo;

@RepositoryRestResource(collectionResourceRel = "tipoarticulos", path = "tipoarticulos")
public interface TipoArticuloRestRepository extends PagingAndSortingRepository<TipoArticulo, Integer>{

}


