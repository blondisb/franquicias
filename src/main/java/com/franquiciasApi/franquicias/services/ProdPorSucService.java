package com.franquiciasApi.franquicias.services;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.franquiciasApi.franquicias.repositories.iProdPorSucRepository;
import com.franquiciasApi.franquicias.repositories.iSucursalesRepository;
import com.franquiciasApi.franquicias.models.ProdPorSucModel; // Import ProdPorSucModel class
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux; // Import Flux
import reactor.core.publisher.Mono; // Import Mono

@Service
@Slf4j
@RequiredArgsConstructor
public class ProdPorSucService {

    private final iProdPorSucRepository prodPorSucRepository;
    private final iSucursalesRepository sucursalesRepository;

    // para guardar o actualizar un producto en una sucursal:
    public Mono<ProdPorSucModel> saveOrUpdateProducto(ProdPorSucModel proXsuc) {

        if (proXsuc.getIdSuc() == null || proXsuc.getIdProd() == null) {
            log.error("El campo 'idSuc' o 'idProd' es nulo para el producto: {}", proXsuc);
            return Mono.error(new RuntimeException("Fields 'idSuc' and 'idProd' must not be null"));
        }

        // Validar que el id de sucursal exista
        return sucursalesRepository.findById(proXsuc.getIdSuc())
                .switchIfEmpty(Mono.error(new RuntimeException("Sucursal with id " + proXsuc.getIdSuc() + " does not exist")))
                .flatMap(sucursal -> 
                    prodPorSucRepository.findByIdProdAndIdSuc(proXsuc.getIdProd(), proXsuc.getIdSuc())
                        .flatMap(existingProducto -> {
                            // Si el producto ya existe en la sucursal, actualizar el stock
                            existingProducto.setStock(existingProducto.getStock() + proXsuc.getStock());
                            return prodPorSucRepository.save(existingProducto)
                                    .doOnNext(updatedProducto -> log.info("Updated stock for Producto: {}", updatedProducto))
                                    .doOnError(error -> log.error("Error updating stock for producto: {}", error.getMessage()));
                        })
                        .switchIfEmpty(
                            // Si no existe, guardar el nuevo registro
                            Mono.just(proXsuc)
                                    .flatMap(producto -> prodPorSucRepository.save(producto))
                                    .doOnNext(savedProducto -> log.info("Saved Producto: {}", savedProducto))
                                    .doOnError(error -> log.error("Error saving producto: {}", error.getMessage()))
                        )
                );
    }

    // para eliminar un producto de una sucursal:
    public Mono<Void> deleteProductoFromSucursal(int idProd, int idSuc) {
        return prodPorSucRepository.findByIdProdAndIdSuc(idProd, idSuc)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto with id " + idProd + " in Sucursal " + idSuc + " not found")))
                .flatMap(existingProducto -> prodPorSucRepository.delete(existingProducto)
                        .doOnSuccess(aVoid -> log.info("Deleted producto with id {} from sucursal {}", idProd, idSuc))
                        .doOnError(error -> log.error("Error deleting producto with id {} from sucursal {}: {}", idProd, idSuc, error.getMessage())));
    }

    public Flux<ProdPorSucModel> getMaxStockByFranquicia(int franquiciaId) {
        return sucursalesRepository.findByFranquiciaid(franquiciaId) // Encuentra todas las sucursales de la franquicia
                .flatMap(sucursal -> prodPorSucRepository.findByIdsuc(sucursal.getId()) // Encuentra productos por sucursal
                        .sort((p1, p2) -> Integer.compare(p2.getStock(), p1.getStock())) // Ordena por stock descendente
                        .next() // Obtiene el producto con más stock
                );
    }
}
