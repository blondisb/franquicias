package com.franquiciasApi.franquicias.services;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.franquiciasApi.franquicias.repositories.iSucursalesRepository;
import com.franquiciasApi.franquicias.models.SucursalesModel; 
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux; 
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class SucursalesService {

    private final iSucursalesRepository sucursalesRepository;

    // para devolver la lista: 
    public Flux<SucursalesModel> getAllSucursales() {
        return sucursalesRepository.findAll()
                .doOnNext(sucursal -> log.info("Sucursal: {}", sucursal))
                .doOnError(error -> log.error("Error devolviendo sucursales: {}", error.getMessage()));
    }

    // para devolver una sucursal por id:
    public Mono<SucursalesModel> getSucursalById(int id) {
        return sucursalesRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Sucursal with id " + id + " not found")))
                .doOnNext(sucursal -> log.info("Sucursal: {}", sucursal))
                .doOnError(error -> log.error("Error retrieving sucursal with id {}: {}", id, error.getMessage()));
    }

    // para guardar una sucursal:
    public Mono<SucursalesModel> saveSucursal(SucursalesModel sucursal) {
        return sucursalesRepository.findByName(sucursal.getName())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RuntimeException("Sucursal with name " + sucursal.getName() + " already exists"));
                    } else {
                        return sucursalesRepository.save(sucursal)
                                .doOnNext(savedSucursal -> log.info("Saved Sucursal: {}", savedSucursal))
                                .doOnError(error -> log.error("Error saving sucursal: {}", error.getMessage()));
                    }
                });
    }

    // para eliminar una sucursal por id:
    public Mono<Void> deleteSucursalById(int id) {
        return sucursalesRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Sucursal with id " + id + " not found")))
                .flatMap(existingSucursal -> sucursalesRepository.deleteById(id)
                        .doOnSuccess(aVoid -> log.info("Deleted sucursal with id {}", id))
                        .doOnError(error -> log.error("Error deleting sucursal with id {}: {}", id, error.getMessage())));
    }

    // para actualizar una sucursal por id:
    public Mono<SucursalesModel> updateSucursal(int id, SucursalesModel sucursal) {
        return sucursalesRepository.findByName(sucursal.getName())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RuntimeException("Sucursal with name " + sucursal.getName() + " already exists"));
                    } else {
                        return sucursalesRepository.findById(id)
                                .flatMap(existingSucursal -> {
                                    existingSucursal.setName(sucursal.getName());
                                    return sucursalesRepository.save(existingSucursal);
                                })
                                .doOnNext(updatedSucursal -> log.info("Updated Sucursal: {}", updatedSucursal))
                                .doOnError(error -> log.error("Error updating sucursal with id {}: {}", id, error.getMessage()));
                    }
                });
    }

}
