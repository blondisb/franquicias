package com.franquiciasApi.franquicias.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import com.franquiciasApi.franquicias.models.FranquiciasModel;

import reactor.core.publisher.Mono;

import org.springframework.stereotype.Repository;

@Repository
public interface iFranquiciasRepository extends R2dbcRepository<FranquiciasModel, Integer> {
    // This interface is empty because it inherits all the necessary methods from ReactiveCrudRepository.
    // You can add custom query methods here if needed.
    // For example, you can define a method to find products by name or price range.

    Mono<FranquiciasModel> findByName(String name);

}
 