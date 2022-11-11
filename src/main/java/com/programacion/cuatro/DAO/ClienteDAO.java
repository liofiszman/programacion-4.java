package com.programacion.cuatro.DAO;

import com.programacion.cuatro.Entities.ClienteEntity;
import com.programacion.cuatro.Repositories.ClienteEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteDAO implements IDAOCliente {

    @Autowired
    private ClienteEntityRepository clienteEntityRepository;

    public int CreateCliente(ClienteEntity p) throws Exception {
        clienteEntityRepository.saveAndFlush(p);
        return p.getId();
    }

    public List<ClienteEntity> ReadClienteList() throws Exception {

        return clienteEntityRepository.findAll();
    }


    public ClienteEntity ReadCliente(Integer id) throws Exception {

        Optional<ClienteEntity> cliente = clienteEntityRepository.findById(id);

        if(cliente.isPresent()) {
            return cliente.get();
        } else {
            return null;
        }

    }

    public ClienteEntity ReadCliente(String nombre) throws Exception {

        Optional<ClienteEntity> cliente = clienteEntityRepository.findByNombre(nombre);

        if(cliente.isPresent()) {
            return cliente.get();
        } else {
            return null;
        }
    }






    public List<ClienteEntity> obtenerClientes() {
        try {
            return ReadClienteList();
        }
        catch (Exception ex) {
            return null;
        }
    }

    public ClienteEntity obtenerCliente(String id) {
        try {
            return ReadCliente(Integer.valueOf(id));
        }
        catch (Exception ex) {
            return null;
        }
    }

    public ClienteEntity obtenerClienteNombre(String nombre) {
        try {
            ClienteEntity cliente =  ReadCliente(nombre);

            if(cliente == null){

                cliente = new ClienteEntity();
                cliente.setNombre("nombre");
                cliente.setApellido("nombre");
                cliente.setDocumento("1234567");
                cliente.setTipoDocumento("DNI");
                CreateCliente(cliente);
            }

            return cliente;
        }
        catch (Exception ex) {
            return null;
        }

    }
}
