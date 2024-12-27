package com.kdnadev.proyectofinal_santiagocabrera.dto.tipo_mascota;

import java.sql.Date;

public class TipoMascotaResponseDTO {
    private Long id;
    private String nombre;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public TipoMascotaResponseDTO() {
    }

    public TipoMascotaResponseDTO(Long id, String nombre, Date fechaCreacion, Date fechaActualizacion) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
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

}
