package com.programacion.cuatro.Business.ReporteStrategy;

import com.programacion.cuatro.DAO.TurnoDAO;
import com.programacion.cuatro.Entities.TurnoEntity;

import java.time.LocalDate;
import java.util.List;

public class ConcreteReporteStrategyMensual implements  IReporteStrategy {
    public List<TurnoEntity> GetTurnos(TurnoDAO turnos) {
        return turnos.obtenerTurnos(
                LocalDate.now().withDayOfMonth(1),
                LocalDate.now().withDayOfMonth(LocalDate.now().getMonth().length(LocalDate.now().isLeapYear())),
                false
        );
    }
}
