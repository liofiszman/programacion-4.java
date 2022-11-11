package com.programacion.cuatro.Classes;

import org.springframework.stereotype.Component;

@Component

public class Cliente extends Persona {

    public Cliente(){

    }
    public Cliente(int id, String nombre, String telefono) {
        this.setId(id);
        this.setNombre(nombre);
        this.setTelefono(telefono);
    }
}
