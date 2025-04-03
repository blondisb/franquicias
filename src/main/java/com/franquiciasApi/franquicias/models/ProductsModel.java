package com.franquiciasApi.franquicias.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("productos") // Replace "products" with your actual table name
public class ProductsModel {

    @Id
    private Integer idproductos; // Ensure this matches your primary key column
    private String name;

    // Getters and setters
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

    // Method to update non-null fields from another Product instance
    public void updateNonNullFields(ProductsModel other) {
        if (other.getName() != null) {
            this.setName(other.getName());
        }
    }
}