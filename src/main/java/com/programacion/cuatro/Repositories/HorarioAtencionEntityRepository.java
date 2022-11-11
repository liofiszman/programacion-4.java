package com.programacion.cuatro.Repositories;

import com.programacion.cuatro.Entities.HorarioAtencionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HorarioAtencionEntityRepository extends JpaRepository<HorarioAtencionEntity, Integer>, JpaSpecificationExecutor<HorarioAtencionEntity> {


    @Query("select h from HorarioAtencionEntity h where h.mecanico.id = ?1")
    List<HorarioAtencionEntity> findByMecanico_Id(Integer id);

}
