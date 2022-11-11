package com.programacion.cuatro.Business;

import com.programacion.cuatro.Business.ReporteStrategy.ConcreteReporteStrategyDiario;
import com.programacion.cuatro.Business.ReporteStrategy.ConcreteReporteStrategyMensual;
import com.programacion.cuatro.Business.ReporteStrategy.IReporteStrategy;
import com.programacion.cuatro.Business.ReporteStrategy.TipoReporteEnum;
import com.programacion.cuatro.Classes.Opcion;
import com.programacion.cuatro.DAO.*;
import com.programacion.cuatro.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class TurnoBusinessObject {
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


    public TurnoBusinessObject() {

    }


    public List<TurnoEntity> obtenerTurnos(String patente) {
        return turnos.obtenerTurnos(patente);
    }

    public List<com.programacion.cuatro.Classes.Turno> obtenerTurnos(Opcion opcion) throws Exception{
        List<MecanicoEntity> opcionesMecanicos = mecanicos.obtenerMecanicosPorEspecialidad(opcion);
        return turnos.obtenerTurnosC(opcion, opcionesMecanicos);
    }

    private IReporteStrategy Strategy;
    private void setStrategy(IReporteStrategy strategy) {
        this.Strategy = strategy; }
    private List<TurnoEntity> executeStrategy() {
        return Strategy.GetTurnos(turnos); }

    public List<TurnoEntity> GetTurnos(TipoReporteEnum tipoReporteEnum) {
        if (tipoReporteEnum == TipoReporteEnum.Diario)
            setStrategy(new ConcreteReporteStrategyDiario());
        if (tipoReporteEnum == TipoReporteEnum.Mensual)
            setStrategy(new ConcreteReporteStrategyMensual());

        return executeStrategy();
    }

    public int addTurno(com.programacion.cuatro.Classes.Turno turno, Opcion opcion) {
        return turnos.addTurno(turno, opcion);
    }

    public List<String> obtenerEspecialidades() {
        return mecanicos.obtenerEspecialidades();
    }

    public List<String> getCompanias() throws Exception {
        return companiasSeguro.obtenerCompaniasSeguro()
                .stream().map(CompaniaSeguroEntity::getNombre).collect(Collectors.toList());
    }
    public CompaniaSeguroEntity obtenerCompaniaSeguro(int id) {
        return companiasSeguro.obtenerCompaniaSeguro(id);
    }

    public void registrarActividades(String numeroTurno,String actividadesText,String insumosText){
        turnos.registrarActividades(numeroTurno, actividadesText, insumosText);
    }

    public FichaConformidadEntity obtenerFichaConformidad(int id) {
        return fichasConformidad.obtenerFichaConformidad(id);
    }
    public void firmaConforme(String numeroTurno){
        turnos.firmaConforme(numeroTurno);
    }
    public void firmaInconforme(String numeroTurno){
        turnos.firmaInconforme(numeroTurno);
    }

    public TurnoEntity obtenerTurno(String id) {
        return turnos.obtenerTurno(id);
    }
    public com.programacion.cuatro.Classes.Turno obtenerTurnoCompleto(String id) {
        return turnos.obtenerTurnoCompleto(id);
    }

    public MecanicoEntity obtenerMecanico(int id) {
        return mecanicos.obtenerMecanico(id);
    }

    public void registrarAsistencia(String id) {
        turnos.registrarAsistencia(id);
    }
    public void cancelarTurno(String id) {
        turnos.cancelarTurno(id);
    }

    public FichaMecanicaEntity obtenerFichaMecanica(int id)  {
        return fichasMecanicas.obtenerFichaMecanica(id);
    }

    public VehiculoEntity obtenerVehiculo(int id)  {
        return vehiculos.obtenerVehiculo(id);
    }

    public String obtenerTurnoID() {
        return turnos.obtenerTurnoID();
    }
}
