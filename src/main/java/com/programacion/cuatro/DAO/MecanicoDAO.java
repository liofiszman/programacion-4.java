package com.programacion.cuatro.DAO;

import com.programacion.cuatro.Classes.Opcion;
import com.programacion.cuatro.Entities.MecanicoEntity;
import com.programacion.cuatro.Repositories.MecanicoEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MecanicoDAO implements IDAOMecanico {

    @Autowired
    private MecanicoEntityRepository mecanicoEntityRepository;



    public List<MecanicoEntity> ReadMecanicoList() throws Exception {


        return mecanicoEntityRepository.findAll();
    }

    public List<String> ReadEspecialidades() throws Exception {

        return mecanicoEntityRepository.findDistinctByOrderByEspecialidadAsc();
    }

    public MecanicoEntity ReadMecanico(Integer id) throws Exception {
        Optional<MecanicoEntity> mecanicoEntity = mecanicoEntityRepository.findById(id);

        if(mecanicoEntity.isPresent()){
            return  mecanicoEntity.get();
        }else {
            return  null;
        }
    }

    public MecanicoEntity ReadMecanicoNombre(String nombre) throws Exception {
        Optional<MecanicoEntity> mecanicoEntity = mecanicoEntityRepository.findByNombre(nombre);

        if(mecanicoEntity.isPresent()){
            return  mecanicoEntity.get();
        }else {
            return  null;
        }
    }

    public List<MecanicoEntity> ReadMecanico(String especialidad) throws Exception {

        return mecanicoEntityRepository.findByEspecialidad(especialidad);

    }



    public List<MecanicoEntity> obtenerMecanicos(){
        try {
            return this.ReadMecanicoList();
        }catch (Exception ex){
            return null;
        }
    }

    public MecanicoEntity obtenerMecanico(String id){
        try {
            return this.ReadMecanico(Integer.valueOf(id));
        }
        catch (Exception ex) {
            return null;
        }
    }

    public MecanicoEntity obtenerMecanico(int id){
        try {
            return this.ReadMecanico(id);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public MecanicoEntity obtenerMecanicoNombre(String nombre){
        try {
            return this.ReadMecanicoNombre(nombre);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public List<String> obtenerEspecialidades(){
        try {return ReadEspecialidades();}
        catch (Exception ex) {return null;}
    }

    public List<MecanicoEntity> obtenerMecanicosPorEspecialidad(Opcion opcion) {
        try{
            return this.ReadMecanico(opcion.getEspecialidad());
        }catch (Exception ex)
        {
            return null;
        }
    }
}
