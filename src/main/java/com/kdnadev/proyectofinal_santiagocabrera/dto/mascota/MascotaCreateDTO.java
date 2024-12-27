package com.kdnadev.proyectofinal_santiagocabrera.dto.mascota;

import org.mapstruct.Mapper;

import com.kdnadev.proyectofinal_santiagocabrera.model.TipoMascota;

@Mapper(componentModel = "spring")
public class MascotaCreateDTO {
    private String nombre;
    private TipoMascota tipoMascota;
    private int edad;
    private boolean disponibleParaAdopcion;

    public MascotaCreateDTO() {
    }

    public MascotaCreateDTO(String nombre, TipoMascota tipoMascota, int edad, boolean disponibleParaAdopcion) {
        this.nombre = nombre;
        this.tipoMascota = tipoMascota;
        this.edad = edad;
        this.disponibleParaAdopcion = disponibleParaAdopcion;
    }

    public TipoMascota getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascota tipoMascota) {
        if (tipoMascota == null) {
            throw new IllegalArgumentException("El tipo de mascota no puede ser nulo");
        }
        this.tipoMascota = tipoMascota;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        this.edad = edad;
    }

    public boolean isDisponibleParaAdopcion() {
        return disponibleParaAdopcion;
    }

    public void setDisponibleParaAdopcion(boolean disponibleParaAdopcion) {
        this.disponibleParaAdopcion = disponibleParaAdopcion;
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
