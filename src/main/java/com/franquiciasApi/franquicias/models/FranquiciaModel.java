package com.franquiciasApi.franquicias.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "franquicias")
public class FranquiciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "franquicia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SucursalModel> sucursales;

    // Constructor sin argumentos
    public FranquiciaModel() {
    }

    // Constructor con parámetros
    public FranquiciaModel(Long id, String nombre, List<SucursalModel> sucursales) {
        this.id = id;
        this.nombre = nombre;
        this.sucursales = sucursales;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<SucursalModel> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<SucursalModel> sucursales) {
        this.sucursales = sucursales;
    }
}
