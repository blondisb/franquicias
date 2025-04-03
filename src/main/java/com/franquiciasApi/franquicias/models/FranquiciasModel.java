package com.franquiciasApi.franquicias.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("franquicias") 
public class FranquiciasModel {

    @Id
    private Integer idfranquicias; 
    private String name;

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

    public void updateNonNullFields(FranquiciasModel other) {
        if (other.getName() != null) {
            this.setName(other.getName());
        }
    }
}