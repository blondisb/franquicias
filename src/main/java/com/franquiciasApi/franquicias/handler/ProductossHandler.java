package com.franquiciasApi.franquicias.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import org.springframework.http.MediaType;
import com.franquiciasApi.franquicias.models.ProductsModel;
import com.franquiciasApi.franquicias.services.ProductosService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductossHandler {

    private final ProductosService productosService;

    // handler: respuesta del server con todos los productss
    public Mono<ServerResponse> getAllProductos(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productosService.getAllProductos(), ProductsModel.class);
    }

    // handler: respuesta del server con un products por id
    public Mono<ServerResponse> getProductsById(ServerRequest request) {
        int id;
        try {
            id = Integer.parseInt(request.pathVariable("id"));
        } catch (NumberFormatException e) {
            return ServerResponse.badRequest().bodyValue("Invalid ID format");
        }
        return productosService.getProductoById(id)
                .flatMap(products -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(products))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    // handler: respuesta del server para guardar un products
    public Mono<ServerResponse> saveProducto(ServerRequest request) {
        return request.bodyToMono(ProductsModel.class)
                .flatMap(products -> productosService.saveProducto(products))
                .flatMap(savedProducts -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(savedProducts))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Error saving products: " + e.getMessage()));
    }

    // handler: respuesta del server para eliminar un products por id
    public Mono<ServerResponse> deleteProductsById(ServerRequest request) {
        int id;
        try {
            id = Integer.parseInt(request.pathVariable("id"));
        } catch (NumberFormatException e) {
            return ServerResponse.badRequest().bodyValue("Invalid ID format");
        }
        return productosService.deleteProductoById(id)
                .then(ServerResponse.ok().build())
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Error deleting products: " + e.getMessage()));
    }

    // handler: respuesta del server para actualizar un products por id
    public Mono<ServerResponse> updateProducts(ServerRequest request) {
        int id;
        try {
            id = Integer.parseInt(request.pathVariable("id"));
        } catch (NumberFormatException e) {
            return ServerResponse.badRequest().bodyValue("Invalid ID format");
        }
        return request.bodyToMono(ProductsModel.class)
                .flatMap(products -> productosService.getProductoById(id)
                        .flatMap(existingProducts -> {
                            if (products != null) {
                                existingProducts.updateNonNullFields(products);
                            }
                            return productosService.updateProducto(id, existingProducts);
                        }))
                .flatMap(updatedProducts -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(updatedProducts))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Error updating products: " + e.getMessage()));
    }
}
