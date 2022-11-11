package com.programacion.cuatro.DAO;

import com.programacion.cuatro.Entities.VehiculoEntity;

import java.util.List;

public interface IDAOVehiculo extends IDAO {
    List<VehiculoEntity> obtenerVehiculos();
    VehiculoEntity obtenerVehiculo(String id);
    VehiculoEntity obtenerVehiculoPatente(String patente, String compania, String cliente);
}
