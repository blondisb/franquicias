package com.franquiciasApi.franquicias.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import com.franquiciasApi.franquicias.models.SucursalesModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@Repository
public interface iSucursalesRepository extends ReactiveCrudRepository<SucursalesModel, Integer> {
    // This interface is empty because it inherits all the necessary methods from ReactiveCrudRepository.
    // You can add custom query methods here if needed.
    // For example, you can define a method to find products by name or price range.

    Mono<SucursalesModel> findByName(String name);
    Flux<SucursalesModel> findByFranquiciaid(int franquiciaid);

}
 