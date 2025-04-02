package com.franquiciasApi.franquicias.repositories;

import com.example.demo.model.FranquiciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFranquiciaRepository extends JpaRepository<FranquiciaModel, Long> {
}
