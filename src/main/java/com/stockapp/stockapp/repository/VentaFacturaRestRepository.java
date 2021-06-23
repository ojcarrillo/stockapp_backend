package com.stockapp.stockapp.repository;

import java.util.Date;

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

	@RestResource(path = "findByNumeroFactura", rel = "ventafactura")
	VentaFactura findByNumeroFactura(String numeroFactura);

	@RestResource(path = "findById", rel = "ventafactura")
	@Query(value = "SELECT * FROM ventasfactura where idVentasFactura = :id", nativeQuery = true)
	public Page<VentaFactura> findByIdFacturasProveedor(@Param("id") String id, Pageable pageable);

	@RestResource(exported = false)
	@Query(value = "SELECT * FROM ventasfactura where fechahoraventa between :fechaInicio and :fechaFin and idvendedor = :idvendedor", nativeQuery = true)
	public Page<VentaFactura> findVentasFacturaByFecha(@Param("fechaInicio") Date fechaInicio,
			@Param("fechaFin") Date fechaFin, Integer idvendedor, Pageable pageable);

	@RestResource(exported = false)
	@Query(value = "SELECT sum(s.basefactura) as totalbase, sum(s.exentototfactura) as totalexento,"  
			+ "sum(s.IvaTotFactura) as totaliva, sum(s.ValorTotFactura) as totalfacturas, " 
			+ "count(s.idVentasFactura) as cantfacturas"
			+ " FROM ventasfactura as s where fechahoraventa between :fechaInicio and :fechaFin and idvendedor = :idvendedor"
			+ " GROUP BY idvendedor ", nativeQuery = true)
	public Object totalesVentasFacturaByFecha(@Param("fechaInicio") Date fechaInicio,
			@Param("fechaFin") Date fechaFin, Integer idvendedor);
}
