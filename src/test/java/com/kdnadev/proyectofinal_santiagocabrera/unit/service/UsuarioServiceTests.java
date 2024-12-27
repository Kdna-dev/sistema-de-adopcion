package com.kdnadev.proyectofinal_santiagocabrera.unit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.kdnadev.proyectofinal_santiagocabrera.service.UsuarioService;
@WebMvcTest(UsuarioService.class)
@ActiveProfiles("test")
public class UsuarioServiceTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;
}
