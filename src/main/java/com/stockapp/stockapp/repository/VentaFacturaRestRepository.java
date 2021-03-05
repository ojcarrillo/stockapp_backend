package com.stockapp.stockapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.stockapp.stockapp.model.VentaFactura;
import com.stockapp.stockapp.projections.VentaFacturaProjection;

@RepositoryRestResource(collectionResourceRel = "ventafactura", path = "ventafactura", excerptProjection = VentaFacturaProjection.class)
public interface VentaFacturaRestRepository extends PagingAndSortingRepository<VentaFactura, Integer> {

	@Override
	@RestResource(exported = false)
	VentaFactura save(VentaFactura factura);

	@RestResource(exported = false)
	VentaFactura findByNumeroFactura(String numeroFactura);

	@RestResource(path = "findById", rel = "ventafactura")
	@Query(value = "SELECT * FROM ventasfactura where idVentasFactura = :id", nativeQuery = true)
	public Page<VentaFactura> findByIdFacturasProveedor(@Param("id") String id, Pageable pageable);

}
