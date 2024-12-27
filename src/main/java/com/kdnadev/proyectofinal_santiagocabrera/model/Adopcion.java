package com.kdnadev.proyectofinal_santiagocabrera.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "adopcion_usuario_mascota")
public class Adopcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idMascota;
    private Long idUsuario;
    private Date fechaAdopcion;

    public Adopcion() {}

    public Adopcion(Long idMascota, Long idUsuario, Date fechaAdopcion) {
        this.idMascota = idMascota;
        this.idUsuario = idUsuario;
        this.fechaAdopcion = fechaAdopcion;
    }

    public Adopcion(Long id, Long idMascota, Long idUsuario, Date fechaAdopcion) {
        this.id = id;
        this.idMascota = idMascota;
        this.idUsuario = idUsuario;
        this.fechaAdopcion = fechaAdopcion;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdMascota() {
        return idMascota;
    }
    public void setIdMascota(Long idMascota) {
        this.idMascota = idMascota;
    }
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Date getFechaAdopcion() {
        return fechaAdopcion;
    }
    public void setFechaAdopcion(Date fechaAdopcion) {
        this.fechaAdopcion = fechaAdopcion;
    }

    @Override
    public String toString() {
        return "Adopcion [id=" + id + ", idMascota=" + idMascota + ", idUsuario=" + idUsuario + ", fechaAdopcion="
                + fechaAdopcion + "]";
    }

}
