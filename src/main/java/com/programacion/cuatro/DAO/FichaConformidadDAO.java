package com.programacion.cuatro.DAO;

import com.programacion.cuatro.Entities.FichaConformidadEntity;
import com.programacion.cuatro.Repositories.FichaConformidadEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FichaConformidadDAO implements IDAOFichaConformidad {

@Autowired
private FichaConformidadEntityRepository fichaConformidadEntityRepository;


    public int CreateFichaConformidad(FichaConformidadEntity p) throws Exception {

     fichaConformidadEntityRepository.saveAndFlush(p);
     return  p.getId();

    }

    public List<FichaConformidadEntity> ReadFichaConformidadList() throws Exception {
        return fichaConformidadEntityRepository.findAll();
    }


    public FichaConformidadEntity ReadFichaConformidad(Integer id) throws Exception {

        Optional<FichaConformidadEntity> fichaConformidad = fichaConformidadEntityRepository.findById(id);

        if(fichaConformidad.isPresent()) {
            return fichaConformidad.get();
        } else {
            return null;
        }

    }



    @Override
    public List<FichaConformidadEntity> obtenerFichasConformidad() {
        try { return ReadFichaConformidadList();}
        catch (Exception ex) {
            return null;}
    }

    @Override
    public FichaConformidadEntity obtenerFichaConformidad(String id) {
        return obtenerFichaConformidad(Integer.valueOf(id));
    }

    public FichaConformidadEntity obtenerFichaConformidad(Integer id) {
        try { return ReadFichaConformidad(id);}
        catch (Exception ex) {return null;}
    }

    public void firmar(FichaConformidadEntity fichaConformidadEntity, boolean conforme) {

        fichaConformidadEntity.setFirmadaConforme(conforme);
        fichaConformidadEntityRepository.saveAndFlush(fichaConformidadEntity);
    }
}
