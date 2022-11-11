package com.programacion.cuatro.Repositories;

import com.programacion.cuatro.Entities.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VehiculoEntityRepository extends JpaRepository<VehiculoEntity, Integer>, JpaSpecificationExecutor<VehiculoEntity> {
    @Query("select v from VehiculoEntity v where v.patente LIKE ?1")
    Optional<VehiculoEntity> findByPatente(String patente);

    Optional<VehiculoEntity> findFirstByOrderByIdDesc();



}
