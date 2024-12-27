package com.kdnadev.proyectofinal_santiagocabrera.dto.mascota;

import java.sql.Date;

import com.kdnadev.proyectofinal_santiagocabrera.model.TipoMascota;


public class MascotaResponseDTO {
    private Long id;
    private String nombre;
    private TipoMascota tipoMascota;
    private int edad;
    private boolean disponibleParaAdopcion;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public MascotaResponseDTO() {
    }

    public MascotaResponseDTO(Long id, String nombre, TipoMascota tipoMascota, int edad, boolean disponibleParaAdopcion,
            Date fechaCreacion, Date fechaActualizacion) {
        this.id = id;
        this.nombre = nombre;
        this.tipoMascota = tipoMascota;
        this.edad = edad;
        this.disponibleParaAdopcion = disponibleParaAdopcion;
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
}
