package com.programacion.cuatro.Repositories;

import com.programacion.cuatro.Entities.FichaMecanicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface FichaMecanicaEntityRepository extends JpaRepository<FichaMecanicaEntity, Integer>, JpaSpecificationExecutor<FichaMecanicaEntity> {

}
