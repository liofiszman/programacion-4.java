package com.programacion.cuatro.Repositories;

import com.programacion.cuatro.Entities.TurnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TurnoEntityRepository extends JpaRepository<TurnoEntity, Integer>, JpaSpecificationExecutor<TurnoEntity> {
    @Query("select t from TurnoEntity t where t.vehiculo.patente = ?1")
    List<TurnoEntity> findByVehiculo_Patente(String patente);

    @Query("select t from TurnoEntity t where t.fecha between ?1 and ?2 and t.active = ?3")
    List<TurnoEntity> findByFechaBetweenAndActive(LocalDate fechaStart, LocalDate fechaEnd, Boolean active);

    @Query("select t from TurnoEntity t where t.fecha between ?1 and ?2")
    List<TurnoEntity> findByFechaBetween(LocalDate fechaStart, LocalDate fechaEnd);

    Optional<TurnoEntity> findFirstByOrderByIdDesc();


}
