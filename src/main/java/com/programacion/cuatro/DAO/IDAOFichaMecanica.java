package com.programacion.cuatro.DAO;

import com.programacion.cuatro.Entities.FichaMecanicaEntity;

import java.util.List;

public interface IDAOFichaMecanica extends IDAO {
    List<FichaMecanicaEntity> obtenerFichasMecanicas();
    FichaMecanicaEntity obtenerFichaMecanica(String id);
    FichaMecanicaEntity obtenerFichaMecanica(int id);
    void registrarActividades(int id, String actividadesText,String insumosText);
}
