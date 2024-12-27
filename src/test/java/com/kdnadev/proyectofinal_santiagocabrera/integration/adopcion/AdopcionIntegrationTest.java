package com.kdnadev.proyectofinal_santiagocabrera.integration.adopcion;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import com.kdnadev.proyectofinal_santiagocabrera.service.AdopcionService;
import com.kdnadev.proyectofinal_santiagocabrera.integration.common.BaseIntegrationTest;
import com.kdnadev.proyectofinal_santiagocabrera.repository.AdopcionRepository;
import com.kdnadev.proyectofinal_santiagocabrera.service.UsuarioService;
import com.kdnadev.proyectofinal_santiagocabrera.service.MascotaService;

@Import({
        AdopcionService.class,
        AdopcionRepository.class,
        UsuarioService.class, // También necesitamos estos porque Adopcion los usa
        MascotaService.class
})
public class AdopcionIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private AdopcionService adopcionService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MascotaService mascotaService;

    @BeforeEach
    void setUp() {
        // Configuración específica para tests de Adopcion
    }
}