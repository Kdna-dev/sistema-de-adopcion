package com.kdnadev.proyectofinal_santiagocabrera.integration.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import com.kdnadev.proyectofinal_santiagocabrera.service.UsuarioService;
import com.kdnadev.proyectofinal_santiagocabrera.integration.common.BaseIntegrationTest;
import com.kdnadev.proyectofinal_santiagocabrera.repository.UsuarioRepository;

@Import({ UsuarioService.class, UsuarioRepository.class })
public class UsuarioIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private UsuarioService usuarioService; // Este será el real, no el mock

    @BeforeEach
    void setUp() {
        // Configuración específica para tests de Usuario
    }

    @Test
    void debeCrearUnUsuarioAdmin() {
        // Test usando el servicio real
    }
}