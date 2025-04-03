package com.franquiciasApi.franquicias.repositories;

import com.franquiciasApi.franquicias.models.SucursalesModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@Repository
public interface iSucursalesRepository extends ReactiveCrudRepository<SucursalesModel, Integer> {

    Mono<SucursalesModel> findByName(String name);
    Flux<SucursalesModel> findByFranquiciaid(int franquiciaid);

}
 