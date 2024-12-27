package com.kdnadev.proyectofinal_santiagocabrera.dto.usuario;

import java.sql.Date;
import java.util.Set;

import com.kdnadev.proyectofinal_santiagocabrera.model.Rol;

public class UsuarioResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String nombre;
    private String documento;
    private String telefono;
    private String cantidadMascotasAdoptadas;
    private Set<Rol> roles;
    private Date fechaCreacion;
    private Date fechaActualizacion;
    
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCantidadMascotasAdoptadas() {
        return cantidadMascotasAdoptadas;
    }
    public void setCantidadMascotasAdoptadas(String cantidadMascotasAdoptadas) {
        this.cantidadMascotasAdoptadas = cantidadMascotasAdoptadas;
    }
    public Set<Rol> getRoles() {
        return roles;
    }
    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
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
