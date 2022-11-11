package com.programacion.cuatro.Entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "CompaniaSeguroEntity")
@Table(name = "compania_seguros", schema = "heroku_c2b540f14439ad1")
public class CompaniaSeguroEntity {
    private Integer id;

    private String nombre;

    private Set<com.programacion.cuatro.Entities.VehiculoEntity> vehiculos = new LinkedHashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "nombre", nullable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(mappedBy = "companiaSeguro")
    public Set<com.programacion.cuatro.Entities.VehiculoEntity> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(Set<com.programacion.cuatro.Entities.VehiculoEntity> vehiculos) {
        this.vehiculos = vehiculos;
    }

}
