package com.stockapp.stockapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stockapp.stockapp.model.Unidad;

@RepositoryRestResource(collectionResourceRel = "unidades", path = "unidades")
public interface UnidadRestRepository extends PagingAndSortingRepository<Unidad, Integer>{

}
