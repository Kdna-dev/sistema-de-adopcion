package com.kdnadev.proyectofinal_santiagocabrera.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import com.kdnadev.proyectofinal_santiagocabrera.repository.AdopcionRepository;
import com.kdnadev.proyectofinal_santiagocabrera.repository.MascotaRepository;
import com.kdnadev.proyectofinal_santiagocabrera.repository.UsuarioRepository;
import com.kdnadev.proyectofinal_santiagocabrera.service.AdopcionService;
import com.kdnadev.proyectofinal_santiagocabrera.service.MascotaService;
import com.kdnadev.proyectofinal_santiagocabrera.service.UsuarioService;

@TestConfiguration
public class TestControllersConfig {

    @Bean
    @Primary
    public UsuarioService usuarioService() {
        return Mockito.mock(UsuarioService.class);
    }

    @Bean
    @Primary
    public MascotaService mascotaService() {
        return Mockito.mock(MascotaService.class);
    }

    @Bean
    @Primary
    public AdopcionService adopcionService() {
        return Mockito.mock(AdopcionService.class);
    }

    @Bean
    @Primary
    public UsuarioRepository usuarioRepository() {
        return Mockito.mock(UsuarioRepository.class);
    }

    @Bean
    @Primary
    public MascotaRepository mascotaRepository() {
        return Mockito.mock(MascotaRepository.class);
    }

    @Bean
    @Primary
    public AdopcionRepository adopcionRepository() {
        return Mockito.mock(AdopcionRepository.class);
    }

}