package com.kdnadev.proyectofinal_santiagocabrera.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.kdnadev.proyectofinal_santiagocabrera.dto.usuario.UsuarioCreateDTO;
import com.kdnadev.proyectofinal_santiagocabrera.model.Rol;
import com.kdnadev.proyectofinal_santiagocabrera.service.UsuarioService;


@Configuration
@Profile("!test")
public class InitialDataConfig {

    private UsuarioService usuarioService;

    public InitialDataConfig (UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @jakarta.annotation.PostConstruct
    public void init() {
        // Verifica si ya existe un admin
        if (usuarioService.findByUsername("admin").isEmpty()) {
            UsuarioCreateDTO admin = new UsuarioCreateDTO();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setEmail("admin@sistema.com");
            admin.setNombre("Administrador");
            admin.setDocumento("0000");
            admin.setTelefono("0000");
            usuarioService.create(admin, Rol.ADMIN.getCodigo());
        }

        //Verifica si ya existe un doctor para testing
        if (usuarioService.findByUsername("doctor").isEmpty()) {
            UsuarioCreateDTO doctor = new UsuarioCreateDTO();
            doctor.setUsername("doctor");
            doctor.setPassword("doctor123");
            doctor.setEmail("doctor@sistema.com");
            doctor.setNombre("Doctor");
            doctor.setDocumento("1234");
            doctor.setTelefono("0000");
            usuarioService.create(doctor, Rol.DOCTOR.getCodigo());
        }

        //Verifica si ya existe un cliente para testing
        if (usuarioService.findByUsername("cliente").isEmpty()) {
            UsuarioCreateDTO cliente = new UsuarioCreateDTO();
            cliente.setUsername("cliente");
            cliente.setPassword("cliente123");
            cliente.setEmail("cliente@sistema.com");
            cliente.setNombre("Cliente");
            cliente.setDocumento("4321");
            cliente.setTelefono("0000");
            usuarioService.create(cliente, Rol.CLIENTE.getCodigo());
        }
    }
}