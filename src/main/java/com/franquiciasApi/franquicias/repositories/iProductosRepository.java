package com.franquiciasApi.franquicias.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.franquiciasApi.franquicias.models.ProductsModel;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Repository;

@Repository
public interface iProductosRepository extends R2dbcRepository<ProductsModel, Integer> {

    Mono<ProductsModel> findByName(String name);

}
 