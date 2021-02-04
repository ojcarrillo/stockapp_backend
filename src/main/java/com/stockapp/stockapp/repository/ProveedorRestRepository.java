package com.stockapp.stockapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stockapp.stockapp.model.Proveedor;

@RepositoryRestResource(collectionResourceRel = "proveedores", path = "proveedores")
public interface ProveedorRestRepository extends PagingAndSortingRepository<Proveedor, Integer> {

}
