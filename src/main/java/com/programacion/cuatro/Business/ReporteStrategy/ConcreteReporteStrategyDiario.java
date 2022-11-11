package com.programacion.cuatro.Business.ReporteStrategy;

import com.programacion.cuatro.DAO.TurnoDAO;
import com.programacion.cuatro.Entities.TurnoEntity;

import java.time.LocalDate;
import java.util.List;

public class ConcreteReporteStrategyDiario implements IReporteStrategy {
    public List<TurnoEntity> GetTurnos(TurnoDAO turnos) {
        return turnos.obtenerTurnos(
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(1),
                false);
    }
}
