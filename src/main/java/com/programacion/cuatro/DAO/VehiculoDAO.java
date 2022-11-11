package com.programacion.cuatro.DAO;

import com.programacion.cuatro.Entities.ClienteEntity;
import com.programacion.cuatro.Entities.CompaniaSeguroEntity;
import com.programacion.cuatro.Entities.VehiculoEntity;
import com.programacion.cuatro.Repositories.VehiculoEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class VehiculoDAO implements IDAOVehiculo {
    @Autowired
    CompaniaSegurosDAO companiaSeguros;
    @Autowired
    ClienteDAO clientes;

    @Autowired
    VehiculoEntityRepository vehiculoEntityRepository;
    public int CreateVehiculo(VehiculoEntity p) throws Exception {

        return vehiculoEntityRepository.saveAndFlush(p).getId();
    }



    public List<VehiculoEntity> ReadVehiculoList() throws Exception {
        return vehiculoEntityRepository.findAll();
    }


    public VehiculoEntity ReadVehiculo(Integer id) throws Exception {

        Optional<VehiculoEntity> vehiculoEntityOptional = vehiculoEntityRepository.findById(id);

        if (vehiculoEntityOptional.isPresent())
            return vehiculoEntityOptional.get();
        else
            return  null;
    }


    public VehiculoEntity GetVehiculoByPatente(String patente) throws Exception {


        Optional<VehiculoEntity> vehiculoEntityOptional = vehiculoEntityRepository.findByPatente(patente);

        if (vehiculoEntityOptional.isPresent())
            return vehiculoEntityOptional.get();
        else
            return  null;
    }

    public List<VehiculoEntity> obtenerVehiculos() {
        try { return ReadVehiculoList(); }
        catch (Exception ex) {return null; }
    }

    public VehiculoEntity obtenerVehiculo(String id) {
        return obtenerVehiculo(Integer.valueOf(id));
    }

    public VehiculoEntity obtenerVehiculo(int id) {
        try {return ReadVehiculo(id);}
        catch (Exception ex) {return null;}
    }

    public VehiculoEntity obtenerVehiculoPatente(String patente, String compania, String nombreCliente) {
        try {
            VehiculoEntity vehiculo = GetVehiculoByPatente(patente);
            if(vehiculo != null)
                return vehiculo;

            CompaniaSeguroEntity companiaSeguro = companiaSeguros.obtenerCompaniaSeguroNombre(compania);
            ClienteEntity cliente = clientes.obtenerClienteNombre(nombreCliente);
            vehiculo = new VehiculoEntity();
            vehiculo.setId(obtenerID() + 1);
            vehiculo.setPatente(patente);
            vehiculo.setCompaniaSeguro(companiaSeguro);
            vehiculo.setCliente(cliente);
            vehiculo.setNumeroPoliza("123");
            vehiculo.setMarca("NN");
            CreateVehiculo(vehiculo);

            return vehiculo;
        }
        catch (Exception ex) {return null;}
    }

    public int obtenerID() {
        try {
            Optional<VehiculoEntity> vehiculoEntityOptional = vehiculoEntityRepository.findFirstByOrderByIdDesc();

            if (vehiculoEntityOptional.isPresent())
                return vehiculoEntityOptional.get().getId();
            else
                return  0;
        }
        catch (Exception ex) {return 0;}
    }
}
