package com.franquiciasApi.franquicias.controllers;

import com.franquiciasApi.franquicias.models.SucursalModel;
import com.franquiciasApi.franquicias.services.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    private final SucursalService sucursalService;

    @Autowired
    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    // Endpoint para consultar todas las sucursales
    @GetMapping
    public ResponseEntity<List<SucursalModel>> getAllSucursales() {
        List<SucursalModel> sucursales = sucursalService.getAllSucursales();
        return ResponseEntity.ok(sucursales);
    }

    // Endpoint para agregar una nueva sucursal
    @PostMapping
    public ResponseEntity<SucursalModel> addSucursal(@RequestBody SucursalModel sucursal) {
        SucursalModel nuevaSucursal = sucursalService.addSucursal(sucursal);
        return ResponseEntity.ok(nuevaSucursal);
    }

    // Endpoint para eliminar una sucursal por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSucursal(@PathVariable Long id) {
        sucursalService.deleteSucursal(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para actualizar el nombre de una sucursal
    @PutMapping("/{id}")
    public ResponseEntity<SucursalModel> updateSucursalName(@PathVariable Long id, @RequestParam String nombre) {
        SucursalModel updated = sucursalService.updateSucursalName(id, nombre);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }
}
