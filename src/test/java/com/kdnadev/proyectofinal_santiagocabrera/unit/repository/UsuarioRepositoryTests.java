package com.kdnadev.proyectofinal_santiagocabrera.unit.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.kdnadev.proyectofinal_santiagocabrera.repository.UsuarioRepository;

@WebMvcTest(UsuarioRepository.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioRepository usuarioRepository;
}
