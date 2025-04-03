package com.franquiciasApi.franquicias.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("productos") 
public class ProductsModel {

    @Id
    private Integer idproductos;
    private String name;

    public Integer getId() {
        return idproductos;
    }

    public void setId(Integer idproductos) {
        this.idproductos = idproductos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateNonNullFields(ProductsModel other) {
        if (other.getName() != null) {
            this.setName(other.getName());
        }
    }
}