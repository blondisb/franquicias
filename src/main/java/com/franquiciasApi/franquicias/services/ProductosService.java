package com.franquiciasApi.franquicias.services;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.franquiciasApi.franquicias.repositories.iProductosRepository;
import com.franquiciasApi.franquicias.models.ProductsModel; 
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux; 
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductosService {

    private final iProductosRepository productosRepository;

    // para devolver la lista: 
    public Flux<ProductsModel> getAllProductos() {
        return productosRepository.findAll()
                .doOnNext(producto -> log.info("Producto: {}", producto))
                .doOnError(error -> log.error("Error devolviendo productos: {}", error.getMessage()));
    }

    // para devolver un producto por id:
    public Mono<ProductsModel> getProductoById(int id) {
        return productosRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto with id " + id + " not found")))
                .doOnNext(producto -> log.info("Producto: {}", producto))
                .doOnError(error -> log.error("Error retrieving producto with id {}: {}", id, error.getMessage()));
    }

    // para guardar un producto:
    public Mono<ProductsModel> saveProducto(ProductsModel producto) {
        return productosRepository.findByName(producto.getName())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RuntimeException("Producto with name " + producto.getName() + " already exists"));
                    } else {
                        return productosRepository.save(producto)
                                .doOnNext(savedProducto -> log.info("Saved Producto: {}", savedProducto))
                                .doOnError(error -> log.error("Error saving producto: {}", error.getMessage()));
                    }
                });
    }

    // para eliminar un producto por id:
    public Mono<Void> deleteProductoById(int id) {
        return productosRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto with id " + id + " not found")))
                .flatMap(existingProducto -> productosRepository.deleteById(id)
                        .doOnSuccess(aVoid -> log.info("Deleted producto with id {}", id))
                        .doOnError(error -> log.error("Error deleting producto with id {}: {}", id, error.getMessage())));
    }

    // para actualizar un producto por id:
    public Mono<ProductsModel> updateProducto(int id, ProductsModel producto) {
        return productosRepository.findByName(producto.getName())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RuntimeException("Producto with name " + producto.getName() + " already exists"));
                    } else {
                        return productosRepository.findById(id)
                                .flatMap(existingProducto -> {
                                    existingProducto.setName(producto.getName());
                                    return productosRepository.save(existingProducto);
                                })
                                .doOnNext(updatedProducto -> log.info("Updated Producto: {}", updatedProducto))
                                .doOnError(error -> log.error("Error updating producto with id {}: {}", id, error.getMessage()));
                    }
                });
    }

}
