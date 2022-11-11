package com.programacion.cuatro.Classes;

import org.springframework.stereotype.Component;

@Component

public class FichaMecanica {

    public FichaMecanica(){}
    public FichaMecanica(int id, String actividades, String repuestos, FichaConformidad fichaConformidad) {
        this.id = id;
        this.actividades = actividades;
        this.repuestos = repuestos;
        this.fichaConformidad = fichaConformidad;
    }

    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private String actividades;
    public String getActividades() {
        return actividades;
    }
    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    private String repuestos;
    public String getRepuestos() {
        return repuestos;
    }
    public void setRepuestos(String repuestos) {
        this.repuestos = repuestos;
    }

    private FichaConformidad fichaConformidad;
    public FichaConformidad getFichaConformidad() {
        return fichaConformidad;
    }
    public void setFichaConformidad(FichaConformidad fichaConformidad) {
        this.fichaConformidad = fichaConformidad;
    }
}
