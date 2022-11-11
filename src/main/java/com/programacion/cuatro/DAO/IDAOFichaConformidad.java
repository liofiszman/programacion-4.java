package com.programacion.cuatro.DAO;

import com.programacion.cuatro.Entities.FichaConformidadEntity;

import java.util.List;

public interface IDAOFichaConformidad extends IDAO {
    List<FichaConformidadEntity> obtenerFichasConformidad();
    FichaConformidadEntity obtenerFichaConformidad(String id);
    FichaConformidadEntity obtenerFichaConformidad(Integer id);
    public void firmar(FichaConformidadEntity fichaConformidadEntity, boolean conforme);
}
