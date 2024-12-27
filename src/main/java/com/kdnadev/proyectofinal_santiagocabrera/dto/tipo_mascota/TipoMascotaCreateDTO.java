package com.kdnadev.proyectofinal_santiagocabrera.dto.tipo_mascota;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class TipoMascotaCreateDTO {
    private String nombre;

    public TipoMascotaCreateDTO() {
    }

    public TipoMascotaCreateDTO(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        this.nombre = nombre;
    }
}
