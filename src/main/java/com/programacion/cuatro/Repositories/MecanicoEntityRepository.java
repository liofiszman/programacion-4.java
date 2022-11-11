package com.programacion.cuatro.Repositories;

import com.programacion.cuatro.Entities.MecanicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MecanicoEntityRepository extends JpaRepository<MecanicoEntity, Integer> {
    @Query("select distinct m.especialidad from MecanicoEntity m order by m.especialidad")
    List<String> findDistinctByOrderByEspecialidadAsc();

    @Query("select m from MecanicoEntity m where m.especialidad LIKE ?1")
    List<MecanicoEntity> findByEspecialidad(String especialidad);

    @Query("select m from MecanicoEntity m where m.nombre = ?1")
    Optional<MecanicoEntity> findByNombre(String nombre);

}
