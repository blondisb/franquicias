package com.franquiciasApi.franquicias.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import com.franquiciasApi.franquicias.models.ProdPorSucModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Repository;

@Repository
public interface iProdPorSucRepository extends R2dbcRepository<ProdPorSucModel, Integer> {
    // This interface is empty because it inherits all the necessary methods from ReactiveCrudRepository.
    // You can add custom query methods here if needed.
    // For example, you can define a method to find products by name or price range.

    Mono<ProdPorSucModel> findByIdProdAndIdSuc(Integer idProd, Integer idSuc);
    Flux<ProdPorSucModel> findByIdsuc(int idSuc);


}
 