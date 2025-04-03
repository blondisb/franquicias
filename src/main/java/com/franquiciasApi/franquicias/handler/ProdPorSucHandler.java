package com.franquiciasApi.franquicias.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import org.springframework.http.MediaType;
import com.franquiciasApi.franquicias.models.ProdPorSucModel; // Asegúrate de importar la clase ProdPorSuc
import com.franquiciasApi.franquicias.services.ProdPorSucService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProdPorSucHandler {

    private final ProdPorSucService prodPorSucService;

    // handler: respuesta del server para guardar un products
    public Mono<ServerResponse> saveOrUpdateProducto(ServerRequest request) {
        return request.bodyToMono(ProdPorSucModel.class)
                .flatMap(prodXsuc -> prodPorSucService.saveOrUpdateProducto(prodXsuc))
                .flatMap(savedProdPorSuc -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(savedProdPorSuc))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Error saving products: " + e.getMessage()));
    }

    // handler: respuesta del server para eliminar un products por id
    public Mono<ServerResponse> deleteLoadutByIds(ServerRequest request) {
        int id;
        try {
            id = Integer.parseInt(request.pathVariable("id"));
        } catch (NumberFormatException e) {
            return ServerResponse.badRequest().bodyValue("Invalid ID format");
        }
        int sucursalId;
        try {
            sucursalId = Integer.parseInt(request.pathVariable("sucursalId"));
        } catch (NumberFormatException e) {
            return ServerResponse.badRequest().bodyValue("Invalid sucursalId format");
        }
        return prodPorSucService.deleteProductoFromSucursal(id, sucursalId)
                .then(ServerResponse.ok().build())
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Error deleting products: " + e.getMessage()));
    }


    public Mono<ServerResponse> getMaxStockByFranquicia(ServerRequest request) {
        int franquiciaId;
        try {
            franquiciaId = Integer.parseInt(request.pathVariable("id"));
        } catch (NumberFormatException e) {
            return ServerResponse.badRequest().bodyValue("Invalid franquicia ID format");
        }
    
        return prodPorSucService.getMaxStockByFranquicia(franquiciaId)
                .collectList()
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Error retrieving max stock: " + e.getMessage()));
    }

}
