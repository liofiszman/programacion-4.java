package com.programacion.cuatro.DAO;

import com.programacion.cuatro.Entities.FichaMecanicaEntity;
import com.programacion.cuatro.Repositories.FichaMecanicaEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FichaMecanicaDAO implements IDAOFichaMecanica {

    @Autowired
    FichaMecanicaEntityRepository fichaMecanicaEntityRepository;


    public int CreateFichaMecanica(FichaMecanicaEntity p) throws Exception {
        return fichaMecanicaEntityRepository.saveAndFlush(p).getId();
    }

    public List<FichaMecanicaEntity> ReadFichaMecanicaList() throws Exception {

        return fichaMecanicaEntityRepository.findAll();
    }


    @Override
    public List<FichaMecanicaEntity> obtenerFichasMecanicas() {
        try {return ReadFichaMecanicaList();}
        catch (Exception ex) {return null;}
    }

    public FichaMecanicaEntity obtenerFichaMecanica(String id) {
        return obtenerFichaMecanica(Integer.valueOf(id));
    };

    public void registrarActividades(int id, String actividadesText,String insumosText) {

        Optional<FichaMecanicaEntity> fichaMecanicaEntity = fichaMecanicaEntityRepository.findById(id);

        if(fichaMecanicaEntity.isPresent()) {
            fichaMecanicaEntity.get().setActividades(actividadesText);
            fichaMecanicaEntity.get().setRepuestos(insumosText);
            fichaMecanicaEntityRepository.saveAndFlush(fichaMecanicaEntity.get());
        }
    }

    public FichaMecanicaEntity obtenerFichaMecanica(int id) {
        try {
            return ReadFichaMecanica(id);
        }
        catch (Exception ex) {
            return null;
        }
    };

    public FichaMecanicaEntity ReadFichaMecanica(Integer id) throws Exception {

        Optional<FichaMecanicaEntity> fichaMecanicaEntity = fichaMecanicaEntityRepository.findById(id);

        if(fichaMecanicaEntity.isPresent()){
            return fichaMecanicaEntity.get();
        }else{
            return  null;
        }
    }



}
