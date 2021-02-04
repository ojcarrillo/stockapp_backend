package com.stockapp.stockapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stockapp.stockapp.model.DatosPrograma;

@RepositoryRestResource(collectionResourceRel = "datosprograma", path = "datosprograma")
public interface DatosProgramaRestRepository extends PagingAndSortingRepository<DatosPrograma, Integer>{

}
