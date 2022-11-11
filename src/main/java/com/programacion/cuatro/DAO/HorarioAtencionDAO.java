package com.programacion.cuatro.DAO;

import com.programacion.cuatro.Entities.HorarioAtencionEntity;
import com.programacion.cuatro.Repositories.HorarioAtencionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HorarioAtencionDAO {


@Autowired
private HorarioAtencionEntityRepository horarioAtencionEntityRepository;

    public List<HorarioAtencionEntity> getByMecanico(Integer mecanico_id) throws Exception{

        return  horarioAtencionEntityRepository.findByMecanico_Id(mecanico_id);

    }










}
