package com.programacion.cuatro.Repositories;

import com.programacion.cuatro.Entities.CompaniaSeguroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompaniaSeguroEntityRepository extends JpaRepository<CompaniaSeguroEntity, Integer>, JpaSpecificationExecutor<CompaniaSeguroEntity> {
    @Query("select c from CompaniaSeguroEntity c where c.nombre = ?1")
    Optional<CompaniaSeguroEntity> findByNombre(String nombre);

}
