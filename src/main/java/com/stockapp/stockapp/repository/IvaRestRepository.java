package com.stockapp.stockapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stockapp.stockapp.model.Iva;

@RepositoryRestResource(collectionResourceRel = "ivas", path = "ivas")
public interface IvaRestRepository extends PagingAndSortingRepository<Iva, Integer> {

}
