package com.franquiciasApi.franquicias.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.franquiciasApi.franquicias.models.ProdPorSucModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Repository;

@Repository
public interface iProdPorSucRepository extends ReactiveCrudRepository<ProdPorSucModel, Integer> {

    Mono<ProdPorSucModel> findByIdProdAndIdSuc(Integer idProd, Integer idSuc);
    Flux<ProdPorSucModel> findByIdsuc(int idSuc);


}
 