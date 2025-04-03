package com.franquiciasApi.franquicias.repositories;

// import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.franquiciasApi.franquicias.models.FranquiciasModel;

import reactor.core.publisher.Mono;

import org.springframework.stereotype.Repository;

@Repository
public interface iFranquiciasRepository extends ReactiveCrudRepository<FranquiciasModel, Integer> {
    
    Mono<FranquiciasModel> findByName(String name);

}
 