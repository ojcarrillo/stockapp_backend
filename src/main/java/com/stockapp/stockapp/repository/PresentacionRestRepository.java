package com.stockapp.stockapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stockapp.stockapp.model.Presentacion;

@RepositoryRestResource(collectionResourceRel = "presentaciones", path = "presentaciones")
public interface PresentacionRestRepository extends PagingAndSortingRepository<Presentacion, Integer>{

}
