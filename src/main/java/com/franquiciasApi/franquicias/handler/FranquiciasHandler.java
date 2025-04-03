package com.franquiciasApi.franquicias.handler;


import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import org.springframework.http.MediaType;
import com.franquiciasApi.franquicias.models.FranquiciasModel;
import com.franquiciasApi.franquicias.services.FranquiciasService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class FranquiciasHandler {

    private final FranquiciasService franquiciasService;

    // handler: respuesta del server con todos los productos
    public Mono<ServerResponse> getAllFranquicias(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(franquiciasService.getAllFranquicias(), FranquiciasModel.class);
    }

    // handler: respuesta del server con un producto por id
    public Mono<ServerResponse> getFranquiciaById(ServerRequest request) {
        int id;
        try {
            id = Integer.parseInt(request.pathVariable("id"));
        } catch (NumberFormatException e) {
            return ServerResponse.badRequest().bodyValue("Invalid ID format");
        }
        return franquiciasService.getFranquiciaById(id)
                .flatMap(franquicia -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(franquicia))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    // handler: respuesta del server para guardar un producto
    public Mono<ServerResponse> saveFranquicia(ServerRequest request) {
        return request.bodyToMono(FranquiciasModel.class)
                .flatMap(franquicia -> franquiciasService.saveFranquicia(franquicia))
                .flatMap(savedFranquicia -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(savedFranquicia))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Error saving franquicia: " + e.getMessage()));
    }

    // handler: respuesta del server para eliminar un producto por id
    public Mono<ServerResponse> deleteFranquiciaById(ServerRequest request) {
        int id;
        try {
            id = Integer.parseInt(request.pathVariable("id"));
        } catch (NumberFormatException e) {
            return ServerResponse.badRequest().bodyValue("Invalid ID format");
        }
        return franquiciasService.deleteFranquiciaById(id)
                .then(ServerResponse.ok().build())
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Error deleting franquicia: " + e.getMessage()));
    }

    // handler: respuesta del server para actualizar un producto por id
    public Mono<ServerResponse> updateFranquicia(ServerRequest request) {
        int id;
        try {
            id = Integer.parseInt(request.pathVariable("id"));
        } catch (NumberFormatException e) {
            return ServerResponse.badRequest().bodyValue("Invalid ID format");
        }
        return request.bodyToMono(FranquiciasModel.class)
                .flatMap(franquicia -> franquiciasService.getFranquiciaById(id)
                        .flatMap(existingFranquicia -> {
                            if (franquicia != null) {
                                existingFranquicia.updateNonNullFields(franquicia);
                            }
                            return franquiciasService.updateFranquicia(id, existingFranquicia);
                        }))
                .flatMap(updatedFranquicia -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(updatedFranquicia))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Error updating franquicia: " + e.getMessage()));
    }
}
