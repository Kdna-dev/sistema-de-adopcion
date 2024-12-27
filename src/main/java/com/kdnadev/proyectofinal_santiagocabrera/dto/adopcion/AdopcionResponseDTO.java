package com.kdnadev.proyectofinal_santiagocabrera.dto.adopcion;

import java.sql.Date;

public class AdopcionResponseDTO {
    private Long id;
    private Long idMascota;
    private Long idUsuario;
    private Date fechaAdopcion;

    public AdopcionResponseDTO() {
    }

    public AdopcionResponseDTO(Long id, Long idMascota, Long idUsuario, Date fechaAdopcion) {
        this.id = id;
        this.idMascota = idMascota;
        this.idUsuario = idUsuario;
        this.fechaAdopcion = fechaAdopcion;
    }

    public Long getId() {
        return this.id;
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

}
