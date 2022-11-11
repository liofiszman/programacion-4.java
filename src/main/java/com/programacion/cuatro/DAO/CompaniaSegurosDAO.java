package com.programacion.cuatro.DAO;

import com.programacion.cuatro.Entities.CompaniaSeguroEntity;
import com.programacion.cuatro.Repositories.CompaniaSeguroEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CompaniaSegurosDAO implements IDAOCompaniaSeguro {

    @Autowired
    private CompaniaSeguroEntityRepository companiaSegurosEntityRepository;


    public List<CompaniaSeguroEntity> ReadCompaniaSeguroList() throws Exception {
       return companiaSegurosEntityRepository.findAll();
    }


    public CompaniaSeguroEntity ReadCompaniaSeguro(Integer id) throws Exception {

        Optional<CompaniaSeguroEntity> companiaSeguro = companiaSegurosEntityRepository.findById(id);

        if(companiaSeguro.isPresent()) {
            return companiaSeguro.get();
        } else {
            return null;
        }
    }

    public CompaniaSeguroEntity ReadCompaniaSeguro(String nombre) throws Exception {
        Optional<CompaniaSeguroEntity> companiaSeguro = companiaSegurosEntityRepository.findByNombre(nombre);

        if(companiaSeguro.isPresent()) {
            return companiaSeguro.get();
        } else {
            return null;
        }
    }





    public List<CompaniaSeguroEntity> obtenerCompaniasSeguro(){
        try {
            return ReadCompaniaSeguroList();
        }
        catch (Exception ex) {
            return null;
        }
    }

    public CompaniaSeguroEntity obtenerCompaniaSeguro(int id){
        try {
            return ReadCompaniaSeguro(id);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public CompaniaSeguroEntity obtenerCompaniaSeguroNombre(String nombre){
        try {
            return ReadCompaniaSeguro(nombre);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
