package com.programacion.cuatro.Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "TurnoEntity")
@Table(name = "turno", schema = "heroku_c2b540f14439ad1")
public class TurnoEntity {
    private Integer id;

    private Boolean active = false;

    private LocalDate fecha;

    private LocalTime hora;

    private com.programacion.cuatro.Entities.MecanicoEntity mecanico;

    private com.programacion.cuatro.Entities.VehiculoEntity vehiculo;

    private Boolean asistencia;

    private com.programacion.cuatro.Entities.FichaMecanicaEntity fichaMecanica;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "active", nullable = false)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Column(name = "fecha", nullable = false)
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Column(name = "hora", nullable = false)
    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "mecanico_id", nullable = false)
    public com.programacion.cuatro.Entities.MecanicoEntity getMecanico() {
        return mecanico;
    }

    public void setMecanico(com.programacion.cuatro.Entities.MecanicoEntity mecanico) {
        this.mecanico = mecanico;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "vehiculo_id", nullable = false)
    public com.programacion.cuatro.Entities.VehiculoEntity getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(com.programacion.cuatro.Entities.VehiculoEntity vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Column(name = "asistencia")
    public Boolean getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Boolean asistencia) {
        this.asistencia = asistencia;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ficha_mecanica_id")
    public com.programacion.cuatro.Entities.FichaMecanicaEntity getFichaMecanica() {
        return fichaMecanica;
    }

    public void setFichaMecanica(com.programacion.cuatro.Entities.FichaMecanicaEntity fichaMecanica) {
        this.fichaMecanica = fichaMecanica;
    }

}
