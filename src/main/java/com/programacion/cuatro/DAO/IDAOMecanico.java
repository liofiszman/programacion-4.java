package com.programacion.cuatro.DAO;

import com.programacion.cuatro.Classes.Opcion;
import com.programacion.cuatro.Entities.MecanicoEntity;

import java.util.List;

public interface IDAOMecanico extends IDAO {
    List<MecanicoEntity> obtenerMecanicos();
    List<MecanicoEntity> obtenerMecanicosPorEspecialidad(Opcion opcion);
    List<String> obtenerEspecialidades();
    MecanicoEntity obtenerMecanico(String id);
    MecanicoEntity obtenerMecanico(int id);
    MecanicoEntity obtenerMecanicoNombre(String nombre);
}
