package com.kdnadev.proyectofinal_santiagocabrera.integration.mascota;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import com.kdnadev.proyectofinal_santiagocabrera.service.MascotaService;
import com.kdnadev.proyectofinal_santiagocabrera.integration.common.BaseIntegrationTest;
import com.kdnadev.proyectofinal_santiagocabrera.repository.MascotaRepository;

@Import({ MascotaService.class, MascotaRepository.class })
public class MascotaIntegrationTest extends BaseIntegrationTest {
    
    @Autowired
    private MascotaService mascotaService;  // Este será el real
    
    @BeforeEach
    void setUp() {
        // Configuración específica para tests de Mascota
    }
} 