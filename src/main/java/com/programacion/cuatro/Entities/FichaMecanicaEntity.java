package com.programacion.cuatro.Entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "FichaMecanicaEntity")
@Table(name = "ficha_mecanica", schema = "heroku_c2b540f14439ad1")
public class FichaMecanicaEntity {
    private Integer id;

    private String actividades;

    private com.programacion.cuatro.Entities.FichaConformidadEntity fichaConformidad;

    private String repuestos;

    private Set<com.programacion.cuatro.Entities.TurnoEntity> turnos = new LinkedHashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "actividades", length = 512)
    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ficha_conformidad_id")
    public com.programacion.cuatro.Entities.FichaConformidadEntity getFichaConformidad() {
        return fichaConformidad;
    }

    public void setFichaConformidad(com.programacion.cuatro.Entities.FichaConformidadEntity fichaConformidad) {
        this.fichaConformidad = fichaConformidad;
    }

    @Column(name = "repuestos", length = 512)
    public String getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(String repuestos) {
        this.repuestos = repuestos;
    }

    @OneToMany(mappedBy = "fichaMecanica")
    public Set<com.programacion.cuatro.Entities.TurnoEntity> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<com.programacion.cuatro.Entities.TurnoEntity> turnos) {
        this.turnos = turnos;
    }

}
