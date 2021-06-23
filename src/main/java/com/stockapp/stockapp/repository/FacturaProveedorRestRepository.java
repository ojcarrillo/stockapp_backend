package com.stockapp.stockapp.repository;





import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.stockapp.stockapp.model.FacturaProveedor;
import com.stockapp.stockapp.model.Proveedor;
import com.stockapp.stockapp.projections.FacturaProveedorProjection;


@RepositoryRestResource(collectionResourceRel = "facturas", path = "facturas", excerptProjection = FacturaProveedorProjection.class)
public interface FacturaProveedorRestRepository extends PagingAndSortingRepository<FacturaProveedor, Integer> {
	
	@Override
    @RestResource(exported=false)
	FacturaProveedor save(FacturaProveedor factura);
	
	@RestResource(exported=false)
	FacturaProveedor findByProveedorAndNumeroFactura(Proveedor proveedor, String numeroFactura);
	
	@RestResource(path = "findById", rel="facturas")
	@Query(value="SELECT * FROM facturasproveedor where idfacturasproveedor = :id", nativeQuery = true)
	public Page<FacturaProveedor> findByIdFacturasProveedor(@Param("id") String id, Pageable pageable);
	
	@RestResource(path = "filtrarByProveedor", rel="facturas")
	@Query(value="SELECT * FROM facturasproveedor where idproveedores = :idproveedor", nativeQuery = true)
	public Page<FacturaProveedor> findByIdProveedor(@Param("idproveedor") String idproveedor, Pageable pageable);
	
	@RestResource(exported=false)
	@Query(value="SELECT * FROM facturasproveedor where facturanum = :numeroFactura", nativeQuery = true)
	public FacturaProveedor findByNumeroFactura(@Param("numeroFactura") String numeroFactura);
}
