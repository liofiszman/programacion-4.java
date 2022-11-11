package com.programacion.cuatro.Repositories;

import com.programacion.cuatro.Entities.FichaConformidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface FichaConformidadEntityRepository extends JpaRepository<FichaConformidadEntity, Integer>, JpaSpecificationExecutor<FichaConformidadEntity> {

}
