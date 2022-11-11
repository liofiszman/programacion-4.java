package com.programacion.cuatro.Entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "MecanicoEntity")
@Table(name = "mecanico", schema = "heroku_c2b540f14439ad1")
public class MecanicoEntity {
    private Integer id;

    private String nombre;

    private String telefono;

    private String apellido;

    private String tipoDocumento;

    private String documento;

    private String especialidad;

    private Set<com.programacion.cuatro.Entities.TurnoEntity> turnos = new LinkedHashSet<>();

    private Set<com.programacion.cuatro.Entities.HorarioAtencionEntity> horarioAtencions = new LinkedHashSet<>();

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

    @Column(name = "telefono", length = 100)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Column(name = "apellido", nullable = false)
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Column(name = "tipo_documento", nullable = false, length = 50)
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Column(name = "documento", nullable = false, length = 100)
    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @Column(name = "especialidad")
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @OneToMany(mappedBy = "mecanico")
    public Set<com.programacion.cuatro.Entities.TurnoEntity> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<com.programacion.cuatro.Entities.TurnoEntity> turnos) {
        this.turnos = turnos;
    }

    @OneToMany(mappedBy = "mecanico")
    public Set<com.programacion.cuatro.Entities.HorarioAtencionEntity> getHorarioAtencions() {
        return horarioAtencions;
    }

    public void setHorarioAtencions(Set<com.programacion.cuatro.Entities.HorarioAtencionEntity> horarioAtencions) {
        this.horarioAtencions = horarioAtencions;
    }

}
