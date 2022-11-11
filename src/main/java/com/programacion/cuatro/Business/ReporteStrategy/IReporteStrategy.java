package com.programacion.cuatro.Business.ReporteStrategy;

import com.programacion.cuatro.DAO.TurnoDAO;
import com.programacion.cuatro.Entities.TurnoEntity;

import java.util.List;

public interface IReporteStrategy {
    List<TurnoEntity> GetTurnos(TurnoDAO turnos);
}
