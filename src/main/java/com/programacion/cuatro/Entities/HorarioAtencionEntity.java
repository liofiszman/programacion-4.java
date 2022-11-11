package com.programacion.cuatro.Entities;

import javax.persistence.*;
import java.time.LocalTime;

@Entity(name = "HorarioAtencionEntity")
@Table(name = "horario_atencion", schema = "heroku_c2b540f14439ad1")
public class HorarioAtencionEntity {
    private Integer id;

    private String diaAtencion;

    private LocalTime horaDesde;

    private LocalTime horaHasta;

    private com.programacion.cuatro.Entities.MecanicoEntity mecanico;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "dia_atencion", nullable = false, length = 100)
    public String getDiaAtencion() {
        return diaAtencion;
    }

    public void setDiaAtencion(String diaAtencion) {
        this.diaAtencion = diaAtencion;
    }

    @Column(name = "hora_desde", nullable = false)
    public LocalTime getHoraDesde() {
        return horaDesde;
    }

    public void setHoraDesde(LocalTime horaDesde) {
        this.horaDesde = horaDesde;
    }

    @Column(name = "hora_hasta", nullable = false)
    public LocalTime getHoraHasta() {
        return horaHasta;
    }

    public void setHoraHasta(LocalTime horaHasta) {
        this.horaHasta = horaHasta;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mecanico_id", nullable = false)
    public com.programacion.cuatro.Entities.MecanicoEntity getMecanico() {
        return mecanico;
    }

    public void setMecanico(com.programacion.cuatro.Entities.MecanicoEntity mecanico) {
        this.mecanico = mecanico;
    }

}
