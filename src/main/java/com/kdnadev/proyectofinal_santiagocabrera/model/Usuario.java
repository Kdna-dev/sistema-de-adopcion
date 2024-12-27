package com.kdnadev.proyectofinal_santiagocabrera.model;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kdnadev.proyectofinal_santiagocabrera.exception.ValidacionNegocioException;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "usuario",
    uniqueConstraints = @UniqueConstraint(columnNames = {"documento"})
)
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    private String nombre;
    private String documento;
    private String telefono;

    private int cantidadMascotasAdoptadas;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Rol> roles = new HashSet<>();

    private Date fechaCreacion;
    private Date fechaActualizacion;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
            .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.name()))
            .toList();
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public Usuario() {}

    public Usuario(String nombre, String documento, String email, String telefono, String username, String password) {
        this.nombre = nombre;
        this.documento = documento;
        this.email = email;
        this.telefono = telefono;
        this.username = username;
        this.password = password;
    }

    public Usuario(Long id, String nombre, String documento, String email, String telefono, String username, String password) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.email = email;
        this.telefono = telefono;
        this.username = username;
        this.password = password;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("El documento no puede estar vacio.");
        }
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", documento=" + documento + ", email=" + email
                + ", telefono=" + telefono + "]";
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles != null ? roles : new HashSet<>();
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        this.password = password;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }
        this.username = username;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void incrementaNumeroDeAdopciones() {
        if (this.cantidadMascotasAdoptadas >= 3)
            throw new ValidacionNegocioException("Usted ya llego al maximo numero de mascotas posible.");
        this.cantidadMascotasAdoptadas++;
    }

    public void decrementaNumeroDeAdopciones(){
        if (this.cantidadMascotasAdoptadas == 0)
            throw new ValidacionNegocioException("El menor numero de mascotas es 0");
        this.cantidadMascotasAdoptadas--;
    }

    public int getCantidadMascotasAdoptadas() {
        return cantidadMascotasAdoptadas;
    }

    public void setCantidadMascotasAdoptadas(int cantidadMascotasAdoptadas) {
        this.cantidadMascotasAdoptadas = cantidadMascotasAdoptadas;
    }
}
