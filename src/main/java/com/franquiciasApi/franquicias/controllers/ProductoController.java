package com.franquiciasApi.franquicias.controllers;

import com.franquiciasApi.franquicias.models.ProductoModel;
import com.franquiciasApi.franquicias.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Endpoint para consultar todos los productos
    @GetMapping
    public ResponseEntity<List<ProductoModel>> getAllProductos() {
        List<ProductoModel> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    // Endpoint para agregar un nuevo producto
    @PostMapping
    public ResponseEntity<ProductoModel> addProducto(@RequestBody ProductoModel producto) {
        ProductoModel nuevoProducto = productoService.addProducto(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    // Endpoint para eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para actualizar el nombre de un producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductoModel> updateProductoName(@PathVariable Long id, @RequestParam String nombre) {
        ProductoModel updated = productoService.updateProductoName(id, nombre);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // Endpoint para obtener el producto con mayor stock por sucursal para una franquicia puntual
    @GetMapping("/franquicia/{franquiciaId}/max-stock")
    public ResponseEntity<List<ProductoModel>> getProductoConMayorStockPorFranquicia(@PathVariable Long franquiciaId) {
        List<ProductoModel> productos = productoService.getProductoConMayorStockPorFranquicia(franquiciaId);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }
}
