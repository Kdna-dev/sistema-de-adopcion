package com.kdnadev.proyectofinal_santiagocabrera.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mascota")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "tipo_mascota_id")
    private TipoMascota tipoMascota;

    private int edad;
    private boolean disponibleParaAdopcion;

    private Date fechaCreacion;
    private Date fechaActualizacion;

    public Mascota() {}

    public Mascota(String nombre, TipoMascota tipoMascota, int edad, boolean disponibleParaAdopcion) {
        this.nombre = nombre;
        this.tipoMascota = tipoMascota;
        this.edad = edad;
        this.disponibleParaAdopcion = disponibleParaAdopcion;
    }

    public Mascota(Long id, String nombre, TipoMascota tipoMascota, int edad, boolean disponibleParaAdopcion) {
        this.id = id;
        this.nombre = nombre;
        this.tipoMascota = tipoMascota;
        this.edad = edad;
        this.disponibleParaAdopcion = disponibleParaAdopcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoMascota getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascota tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isDisponibleParaAdopcion() {
        return disponibleParaAdopcion;
    }

    public void setDisponibleParaAdopcion(boolean disponibleParaAdopcion) {
        this.disponibleParaAdopcion = disponibleParaAdopcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public String toString() {
        return "Mascota [id=" + id + ", nombre=" + nombre + ", tipoMascota=" + tipoMascota + ", edad=" + edad
                + ", disponibleParaAdopcion=" + disponibleParaAdopcion + "]";
    }

}
