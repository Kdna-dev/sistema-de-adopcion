package com.kdnadev.proyectofinal_santiagocabrera.dto.adopcion;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class AdopcionCreateDTO {
    private Long idMascota;
    private Long idUsuario;

    public AdopcionCreateDTO() {
    }
    public AdopcionCreateDTO(Long idMascota, Long idUsuario) {
        this.idMascota = idMascota;
        this.idUsuario = idUsuario;
    }

    public Long getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Long idMascota) {
        if (idMascota == null) {
            throw new IllegalArgumentException("El ID de mascota no puede ser nulo");
        }
        this.idMascota = idMascota;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        if (idUsuario == null) {
            throw new IllegalArgumentException("El ID de usuario no puede ser nulo");
        }
        this.idUsuario = idUsuario;
    }
}
