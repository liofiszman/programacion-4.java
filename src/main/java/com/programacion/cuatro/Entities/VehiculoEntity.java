package com.programacion.cuatro.Entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "VehiculoEntity")
@Table(name = "vehiculo", schema = "heroku_c2b540f14439ad1")
public class VehiculoEntity {
    private Integer id;

    private com.programacion.cuatro.Entities.CompaniaSeguroEntity companiaSeguro;

    private com.programacion.cuatro.Entities.ClienteEntity cliente;

    private String numeroPoliza;

    private String marca;

    private String patente;

    private Set<com.programacion.cuatro.Entities.TurnoEntity> turnos = new LinkedHashSet<>();

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "compania_seguro_id", nullable = false)
    public com.programacion.cuatro.Entities.CompaniaSeguroEntity getCompaniaSeguro() {
        return companiaSeguro;
    }

    public void setCompaniaSeguro(com.programacion.cuatro.Entities.CompaniaSeguroEntity companiaSeguro) {
        this.companiaSeguro = companiaSeguro;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    public com.programacion.cuatro.Entities.ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(com.programacion.cuatro.Entities.ClienteEntity cliente) {
        this.cliente = cliente;
    }

    @Column(name = "numero_poliza", nullable = false, length = 100)
    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    @Column(name = "marca", nullable = false)
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Column(name = "patente", nullable = false, length = 50)
    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    @OneToMany(mappedBy = "vehiculo")
    public Set<com.programacion.cuatro.Entities.TurnoEntity> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<com.programacion.cuatro.Entities.TurnoEntity> turnos) {
        this.turnos = turnos;
    }

}
