package com.franquiciasApi.franquicias.models;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class ProductoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private SucursalModel sucursal;

    // Constructor sin argumentos
    public ProductoModel() {
    }

    // Constructor con parámetros
    public ProductoModel(Long id, String nombre, Integer stock, SucursalModel sucursal) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.sucursal = sucursal;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public SucursalModel getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalModel sucursal) {
        this.sucursal = sucursal;
    }
}
