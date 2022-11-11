package com.programacion.cuatro.DAO;


import com.programacion.cuatro.Entities.CompaniaSeguroEntity;

import java.util.List;

public interface IDAOCompaniaSeguro extends IDAO {
    List<CompaniaSeguroEntity> obtenerCompaniasSeguro();
    CompaniaSeguroEntity obtenerCompaniaSeguro(int id);

    CompaniaSeguroEntity obtenerCompaniaSeguroNombre(String nombre);
}
