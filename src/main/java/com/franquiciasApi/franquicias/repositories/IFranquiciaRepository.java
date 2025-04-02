package com.franquiciasApi.franquicias.repositories;

import com.franquiciasApi.franquicias.models.FranquiciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFranquiciaRepository extends JpaRepository<FranquiciaModel, Long> {
}
