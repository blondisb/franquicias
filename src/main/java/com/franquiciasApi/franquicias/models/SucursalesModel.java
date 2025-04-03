package com.franquiciasApi.franquicias.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.sql.In;

import lombok.NonNull;

@Table("sucursales") // Replace "products" with your actual table name
public class SucursalesModel {

    @Id
    private Integer idsucursales; // Ensure this matches your primary key column
    private String name;

    @NonNull
    private Integer franquiciaid; // Foreign key to the franquicias table

    // Getters and setters
    public Integer getId() {
        return idsucursales;
    }

    public void setId(Integer idsucursales) {
        this.idsucursales = idsucursales;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getfranquiciaid() {
        return franquiciaid;
    }

    public void setfranquiciaid(Integer franquiciaid) {
        this.franquiciaid = franquiciaid;
    }

    // Method to update non-null fields from another Product instance
    public void updateNonNullFields(SucursalesModel other) {
        if (other.getName() != null) {
            this.setName(other.getName());
        }
    }
}