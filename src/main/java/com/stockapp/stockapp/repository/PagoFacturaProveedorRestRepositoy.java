package com.stockapp.stockapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.stockapp.stockapp.model.PagoFacturaProveedor;
import com.stockapp.stockapp.projections.PagoFacturaProveedorProjection;

@RepositoryRestResource(collectionResourceRel = "pagosfacturapoveedor", path = "pagosfacturapoveedor", excerptProjection = PagoFacturaProveedorProjection.class)
public interface PagoFacturaProveedorRestRepositoy extends PagingAndSortingRepository<PagoFacturaProveedor, Integer> {

	@Override
	@RestResource(exported = false)
	PagoFacturaProveedor save(PagoFacturaProveedor pago);

	@RestResource(exported = false)
	@Query(value = "SELECT COALESCE(sum(ValorPago) + SUM(Descuento),0) as saldo_acumulado FROM pagofactproveedor WHERE 1 "
			+ "and IdFacturasProveedor = :idfactura " + "group by IdFacturasProveedor", nativeQuery = true)
	Double getTotalPagosAcumuladosByFactura(@Param("idfactura") Integer idfacturaproveedor);

	@RestResource(exported = false)
	@Query(value = "SELECT distinct pago as estado, count(distinct fp.idFacturasProveedor) as cantidad, sum(COALESCE(pfp.ValorPago,0)) as total "
			+ " FROM facturasproveedor fp left join pagofactproveedor pfp on pfp.IdFacturasProveedor = fp.idFacturasProveedor "
			+ " where fp.idProveedores = :idproveedor " + "" + "GROUP by fp.pago ORDER by pago", nativeQuery = true)
	List<Object[]> getTotalPagosByProveedor(@Param("idproveedor") Integer idproveedor);
	
	
	@RestResource(path = "filtrarByFactuta", rel="pagosfacturapoveedor")
	@Query(value = "SELECT * FROM pagofactproveedor WHERE IdFacturasProveedor = :idfactura ORDER BY fechacancelacion asc ", nativeQuery = true)
	List<PagoFacturaProveedor> obtenerPagosByFactura(@Param("idfactura") Integer idfacturaproveedor);
}
