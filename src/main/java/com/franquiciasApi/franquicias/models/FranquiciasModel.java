package com.franquiciasApi.franquicias.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("franquicias") // Replace "products" with your actual table name
public class FranquiciasModel {

    @Id
    private Integer idfranquicias; // Ensure this matches your primary key column
    private String name;

    // Getters and setters
    public Integer getId() {
        return idfranquicias;
    }

    public void setId(Integer idfranquicias) {
        this.idfranquicias = idfranquicias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Method to update non-null fields from another Product instance
    public void updateNonNullFields(FranquiciasModel other) {
        if (other.getName() != null) {
            this.setName(other.getName());
        }
    }
}