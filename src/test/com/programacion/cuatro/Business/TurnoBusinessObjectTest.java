package com.programacion.cuatro.Business;

import com.programacion.cuatro.DAO.*;
import com.programacion.cuatro.Entities.TurnoEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TurnoBusinessObjectTest {

    @Autowired
    TurnoDAO turnos;
    @Autowired
    MecanicoDAO mecanicos;
    @Autowired
    CompaniaSegurosDAO companiasSeguro;
    @Autowired
    VehiculoDAO vehiculos;
    @Autowired
    FichaMecanicaDAO fichasMecanicas;
    @Autowired
    FichaConformidadDAO fichasConformidad;

    @Test
    void obtenerEspecialidades() {

        //Verifico que tengo al menos una especialidad.
        assertNotEquals(0, mecanicos.obtenerEspecialidades().size());

    }

    @Test
    void getCompanias() {

        //Verifico que tengo al menos una compania de seguro.
        assertNotEquals(0, companiasSeguro.obtenerCompaniasSeguro().size());
    }

    @Test
    void obtenerTurnoID() {

        //Verifico que tengo al menos un turno.
        assertNotEquals(null, turnos.obtenerTurnoID());
    }

    @Test
    public void firmaConforme(){

        //Verifico que la ficha no este ya firmada cuando se quiere firmar conforme .
        TurnoEntity turno = turnos.obtenerTurno("94");
        boolean firmada = turno.getFichaMecanica().getFichaConformidad().getFirmada();
        assert(firmada) != false;
    }

@Test
    public void obtenerTurno() {
        //Verifico que el turno existe
        TurnoEntity turno = turnos.obtenerTurno("104");
        assert(turno).getId() != null;
    }
}
