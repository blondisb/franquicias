package com.franquiciasApi.franquicias.repositories;

import com.franquiciasApi.franquicias.models.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<ProductoModel, Long> {

    // Consulta para obtener, para cada sucursal de la franquicia indicada, el producto que tenga mayor stock.
    @Query("SELECT p FROM ProductoModel p " +
           "WHERE p.sucursal.franquicia.id = :franquiciaId " +
           "AND p.stock = (SELECT MAX(p2.stock) FROM ProductoModel p2 WHERE p2.sucursal.id = p.sucursal.id)")
    List<ProductoModel> findProductoConMayorStockPorSucursal(@Param("franquiciaId") Long franquiciaId);
}
