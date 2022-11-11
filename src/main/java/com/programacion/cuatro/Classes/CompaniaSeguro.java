package com.programacion.cuatro.Classes;

import org.springframework.stereotype.Component;

@Component

public class CompaniaSeguro {

    public CompaniaSeguro(){

    }
    public CompaniaSeguro(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private String nombre;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
