package com.kdnadev.proyectofinal_santiagocabrera.dto.mascota;

import com.kdnadev.proyectofinal_santiagocabrera.model.TipoMascota;

public class MascotaUpdateDTO {
    private String nombre;
    private TipoMascota tipoMascota;
    private Integer edad;
    private Boolean disponibleParaAdopcion;

    public MascotaUpdateDTO() {
    }

    public MascotaUpdateDTO(String nombre, TipoMascota tipoMascota, int edad) {
        this.nombre = nombre;
        this.tipoMascota = tipoMascota;
        this.edad = edad;
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Boolean isDisponibleParaAdopcion() {
        return disponibleParaAdopcion;
    }

    public void setDisponibleParaAdopcion(Boolean disponibleParaAdopcion) {
        this.disponibleParaAdopcion = disponibleParaAdopcion;
    }

}
