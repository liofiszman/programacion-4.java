package com.programacion.cuatro.Repositories;

import com.programacion.cuatro.Entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClienteEntityRepository extends JpaRepository<ClienteEntity, Integer>, JpaSpecificationExecutor<ClienteEntity> {
    @Query("select c from ClienteEntity c where c.nombre LIKE ?1")
    Optional<ClienteEntity> findByNombre(String nombre);

}
