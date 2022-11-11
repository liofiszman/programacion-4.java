package com.programacion.cuatro.Entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "FichaConformidadEntity")
@Table(name = "ficha_conformidad", schema = "heroku_c2b540f14439ad1")
public class FichaConformidadEntity {
    private Integer id;

    private String motivosDisconforme;

    private Boolean firmada;

    private Boolean firmadaConforme;

    private Set<com.programacion.cuatro.Entities.FichaMecanicaEntity> fichaMecanicas = new LinkedHashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "motivos_disconforme")
    public String getMotivosDisconforme() {
        return motivosDisconforme;
    }

    public void setMotivosDisconforme(String motivosDisconforme) {
        this.motivosDisconforme = motivosDisconforme;
    }

    @Column(name = "firmada")
    public Boolean getFirmada() {
        return firmada;
    }

    public void setFirmada(Boolean firmada) {
        this.firmada = firmada;
    }

    @Column(name = "firmada_conforme")
    public Boolean getFirmadaConforme() {
        return firmadaConforme;
    }

    public void setFirmadaConforme(Boolean firmadaConforme) {
        this.firmadaConforme = firmadaConforme;
    }

    @OneToMany(mappedBy = "fichaConformidad")
    public Set<com.programacion.cuatro.Entities.FichaMecanicaEntity> getFichaMecanicas() {
        return fichaMecanicas;
    }

    public void setFichaMecanicas(Set<com.programacion.cuatro.Entities.FichaMecanicaEntity> fichaMecanicas) {
        this.fichaMecanicas = fichaMecanicas;
    }

}
