package com.programacion.cuatro.DAO;

import com.programacion.cuatro.Classes.Opcion;
import com.programacion.cuatro.Classes.Turno;
import com.programacion.cuatro.Entities.MecanicoEntity;
import com.programacion.cuatro.Entities.TurnoEntity;

import java.time.LocalDate;
import java.util.List;

public interface IDAOTurno extends IDAO {
    List<TurnoEntity> obtenerTurnos(String patente);
    List<TurnoEntity> obtenerTurnos(LocalDate fechaDesde, LocalDate fechaHasta, boolean onlyActive);
    List<Turno> obtenerTurnosC(Opcion opcion, List<MecanicoEntity> mecanicos) throws Exception;
    TurnoEntity obtenerTurno(String id);
    com.programacion.cuatro.Classes.Turno obtenerTurnoCompleto(String id);
    void registrarAsistencia(String id);
    void cancelarTurno(String id);
    int addTurno(com.programacion.cuatro.Classes.Turno turno, Opcion opcion);
    void registrarActividades(String numeroTurno, String actividadesText, String insumosText);

    void firmaConforme(String numeroTurno);
    void firmaInconforme(String numeroTurno);

    String obtenerTurnoID();
}
