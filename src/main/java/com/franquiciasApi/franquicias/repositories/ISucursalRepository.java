package com.franquiciasApi.franquicias.repositories;

import com.franquiciasApi.franquicias.models.SucursalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISucursalRepository extends JpaRepository<SucursalModel, Long> {
}
