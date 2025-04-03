package com.franquiciasApi.franquicias.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import org.springframework.http.MediaType;
import com.franquiciasApi.franquicias.models.SucursalesModel;
import com.franquiciasApi.franquicias.services.SucursalesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class SucursalesHandler {

    private final SucursalesService sucursalesService;

    // handler: respuesta del server con todos los productos
    public Mono<ServerResponse> getAllSucursales(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(sucursalesService.getAllSucursales(), SucursalesModel.class);
    }

    // handler: respuesta del server con un producto por id
    public Mono<ServerResponse> getSucursalById(ServerRequest request) {
        int id;
        try {
            id = Integer.parseInt(request.pathVariable("id"));
        } catch (NumberFormatException e) {
            return ServerResponse.badRequest().bodyValue("Invalid ID format");
        }
        return sucursalesService.getSucursalById(id)
                .flatMap(sucursal -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(sucursal))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    // handler: respuesta del server para guardar un producto
    public Mono<ServerResponse> saveSucursal(ServerRequest request) {
        return request.bodyToMono(SucursalesModel.class)
                .flatMap(sucursal -> sucursalesService.saveSucursal(sucursal))
                .flatMap(savedSucursal -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(savedSucursal))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Error saving sucursal: " + e.getMessage()));
    }

    // handler: respuesta del server para eliminar un producto por id
    public Mono<ServerResponse> deleteSucursalById(ServerRequest request) {
        int id;
        try {
            id = Integer.parseInt(request.pathVariable("id"));
        } catch (NumberFormatException e) {
            return ServerResponse.badRequest().bodyValue("Invalid ID format");
        }
        return sucursalesService.deleteSucursalById(id)
                .then(ServerResponse.ok().build())
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Error deleting sucursal: " + e.getMessage()));
    }

    // handler: respuesta del server para actualizar un producto por id
    public Mono<ServerResponse> updateSucursal(ServerRequest request) {
        int id;
        try {
            id = Integer.parseInt(request.pathVariable("id"));
        } catch (NumberFormatException e) {
            return ServerResponse.badRequest().bodyValue("Invalid ID format");
        }
        return request.bodyToMono(SucursalesModel.class)
                .flatMap(sucursal -> sucursalesService.getSucursalById(id)
                        .flatMap(existingSucursal -> {
                            if (sucursal != null) {
                                existingSucursal.updateNonNullFields(sucursal);
                            }
                            return sucursalesService.updateSucursal(id, existingSucursal);
                        }))
                .flatMap(updatedSucursal -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(updatedSucursal))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Error updating sucursal: " + e.getMessage()));
    }
}
