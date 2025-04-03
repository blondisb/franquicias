package com.franquiciasApi.franquicias.services;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.franquiciasApi.franquicias.repositories.iFranquiciasRepository;
import com.franquiciasApi.franquicias.models.FranquiciasModel; 
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux; 
import reactor.core.publisher.Mono; 

@Service
@Slf4j
@RequiredArgsConstructor
public class FranquiciasService {

    private final iFranquiciasRepository franquiciasRepository;

    // para devolver la lista: 
    public Flux<FranquiciasModel> getAllFranquicias() {
        return franquiciasRepository.findAll()
                .doOnNext(franquicia -> log.info("Franquicia: {}", franquicia))
                .doOnError(error -> log.error("Error devolviendo franquicias: {}", error.getMessage()));
    }

    // para devolver una franquicia por id:
    public Mono<FranquiciasModel> getFranquiciaById(int id) {
        return franquiciasRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Franquicia with id " + id + " not found")))
                .doOnNext(franquicia -> log.info("Franquicia: {}", franquicia))
                .doOnError(error -> log.error("Error retrieving franquicia with id {}: {}", id, error.getMessage()));
    }

    // para guardar una franquicia:
    public Mono<FranquiciasModel> saveFranquicia(FranquiciasModel franquicia) {
        return franquiciasRepository.findByName(franquicia.getName())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RuntimeException("Franquicia with name " + franquicia.getName() + " already exists"));
                    } else {
                        return franquiciasRepository.save(franquicia)
                                .doOnNext(savedFranquicia -> log.info("Saved Franquicia: {}", savedFranquicia))
                                .doOnError(error -> log.error("Error saving franquicia: {}", error.getMessage()));
                    }
                });
    }

    // para eliminar una franquicia por id:
    public Mono<Void> deleteFranquiciaById(int id) {
        return franquiciasRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Franquicia with id " + id + " not found")))
                .flatMap(existingFranquicia -> franquiciasRepository.deleteById(id)
                        .doOnSuccess(aVoid -> log.info("Deleted franquicia with id {}", id))
                        .doOnError(error -> log.error("Error deleting franquicia with id {}: {}", id, error.getMessage())));
    }

    // para actualizar una franquicia por id:
    public Mono<FranquiciasModel> updateFranquicia(int id, FranquiciasModel franquicia) {
        return franquiciasRepository.findByName(franquicia.getName())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RuntimeException("Franquicia with name " + franquicia.getName() + " already exists"));
                    } else {
                        return franquiciasRepository.findById(id)
                                .flatMap(existingFranquicia -> {
                                    existingFranquicia.setName(franquicia.getName());
                                    return franquiciasRepository.save(existingFranquicia);
                                })
                                .doOnNext(updatedFranquicia -> log.info("Updated Franquicia: {}", updatedFranquicia))
                                .doOnError(error -> log.error("Error updating franquicia with id {}: {}", id, error.getMessage()));
                    }
                });
    }

}
