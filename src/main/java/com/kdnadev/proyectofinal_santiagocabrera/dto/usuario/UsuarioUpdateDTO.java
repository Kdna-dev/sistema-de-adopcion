package com.kdnadev.proyectofinal_santiagocabrera.dto.usuario;

public class UsuarioUpdateDTO {
    private String email;
    private String nombre;
    private String telefono;

    public UsuarioUpdateDTO() {
    }

    public UsuarioUpdateDTO(String email, String nombre, String telefono) {
        this.email = email;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
