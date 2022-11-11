package com.programacion.cuatro.DAO;

import com.programacion.cuatro.Entities.ClienteEntity;

import java.util.List;

public interface IDAOCliente extends IDAO {
    List<ClienteEntity> obtenerClientes();
    ClienteEntity obtenerCliente(String id);
    ClienteEntity obtenerClienteNombre(String nombre);
}
