package com.franquiciasApi.franquicias.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.NonNull;

@Table("productos_por_sucursal") // Replace "products" with your actual table name
public class ProdPorSucModel {

    @Id
    private Integer idproductos_por_sucursal; // Ensure this matches your primary key column

    @NonNull
    private Integer idprod;

    @NonNull
    private Integer idsuc;

    private Integer stock; // Optional field, can be null

    // Getters and setters
    public Integer getId() {
        return idproductos_por_sucursal;
    }

    public void setId(Integer idproductos_por_sucursal) {
        this.idproductos_por_sucursal = idproductos_por_sucursal;
    }

    public Integer getIdProd() {
        return idprod;
    }

    public void setIdProd(Integer idprod) {
        this.idprod = idprod;
    }

    public Integer getIdSuc() {
        return idsuc;
    }

    public void setIdSuc(Integer idsuc) {
        this.idsuc = idsuc;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

}