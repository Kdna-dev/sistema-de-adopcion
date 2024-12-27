package com.kdnadev.proyectofinal_santiagocabrera.unit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdnadev.proyectofinal_santiagocabrera.model.Rol;
import com.kdnadev.proyectofinal_santiagocabrera.model.Usuario;
import com.kdnadev.proyectofinal_santiagocabrera.service.UsuarioService;
import com.kdnadev.proyectofinal_santiagocabrera.config.TestSecurityConfig;
import com.kdnadev.proyectofinal_santiagocabrera.controller.UsuarioController;

import org.springframework.context.annotation.Import;

@WebMvcTest(UsuarioController.class)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
class UsuarioControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    //region GetMethods
    @Test
    void debeRetornarUnauthorizedSinCredenciales() throws Exception {
        mockMvc.perform(get("/api/usuarios"))
            .andExpect(status().isUnauthorized())
            .andDo(print());
    }

    @Test
    void debeObtenerTodosLosUsuariosLogueadoAdmin() throws Exception {
        Usuario admin = new Usuario();
        admin.setUsername("admin");
        admin.setRoles(Set.of(Rol.ADMIN));

        Usuario cliente = new Usuario();
        cliente.setUsername("cliente");
        cliente.setRoles(Set.of(Rol.CLIENTE));

        when(usuarioService.getAll()).thenReturn(Arrays.asList(admin, cliente));

        mockMvc.perform(get("/api/usuarios")
                .with(user("admin").password("admin123").roles("ADMIN")))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].username").value("admin"))
            .andExpect(jsonPath("$[1].username").value("cliente"))
            .andDo(print());
    }

    @Test
    void debeRetornarForbiddenLogueadoDoctor() throws Exception {
        mockMvc.perform(get("/api/usuarios")
                .with(user("doctor").password("doctor123").roles("DOCTOR")))
            .andExpect(status().isForbidden())
            .andDo(print());
    }

    @Test
    void debeRetornarForbiddenLogueadoCliente() throws Exception {
        mockMvc.perform(get("/api/usuarios")
                .with(user("cliente").password("cliente123").roles("CLIENTE")))
            .andExpect(status().isForbidden())
            .andDo(print());
    }

    @Test
    void debeObtenerUsuarioExistentePorId() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(8L);
        usuario.setUsername("test");

        when(usuarioService.findById(8L)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/api/usuarios/8")
                .with(user("admin").password("admin123").roles("ADMIN")))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(8))
            .andExpect(jsonPath("$.username").value("test"))
            .andDo(print());
    }

    @Test
    void intentaObtenerUsuarioInexistentePorId() throws Exception {
        when(usuarioService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/usuarios/1")
                .with(user("admin").password("admin123").roles("ADMIN")))
            .andExpect(status().isNotFound())
            .andDo(print());
    }
    //endregion

    //region Post Methods
    @Test
    void debeRetornarBadRequestCuandoPasswordEsVacio() throws Exception {
        // Arrange
        // Usar Map o JsonObject para evitar la validación del modelo
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "test");
        requestBody.put("password", "");
        requestBody.put("documento", "1234");

        // Act & Assert
        mockMvc.perform(post("/api/usuarios/registro/admin")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.exito").value(false))
            .andExpect(jsonPath("$.mensaje").value("La contraseña no puede estar vacía"))
            .andExpect(jsonPath("$.error").value("Validación fallida"))
            .andExpect(jsonPath("$.timestamp").exists())
            .andExpect(jsonPath("$.data").doesNotExist())
            .andDo(print());
    }

    @Test
    void debeRetornarBadRequestCuandoDocumentoEsVacio() throws Exception {
        // Arrange
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "test");
        requestBody.put("password", "test123");
        requestBody.put("documento", "");

        // Act & Assert
        mockMvc.perform(post("/api/usuarios/registro/admin")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.exito").value(false))
            .andExpect(jsonPath("$.mensaje").value("El documento no puede estar vacio."))
            .andExpect(jsonPath("$.error").value("Validación fallida"))
            .andExpect(jsonPath("$.timestamp").exists())
            .andExpect(jsonPath("$.data").doesNotExist())
            .andDo(print());
    }

    @Test
    void debeRetornarBadRequestCuandoUsernameEsVacio() throws Exception {
        // Arrange
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "");
        requestBody.put("password", "test123");
        requestBody.put("documento", "1234");

        // Act & Assert
        mockMvc.perform(post("/api/usuarios/registro/admin")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.exito").value(false))
            .andExpect(jsonPath("$.mensaje").value("El nombre de usuario no puede estar vacío"))
            .andExpect(jsonPath("$.error").value("Validación fallida"))
            .andExpect(jsonPath("$.timestamp").exists())
            .andExpect(jsonPath("$.data").doesNotExist())
            .andDo(print());
    }

    @Test
    void debeCrearUsuarioAdmin() throws Exception {
        // Arrange
        Map<String, String> requestBody = new HashMap<>();
        // Datos requeridos como minimo para crear un usuario
        requestBody.put("username", "newadmin");
        requestBody.put("password", "admin123");
        requestBody.put("documento", "1234567890");

        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");

        // Act & Assert
        mockMvc.perform(post("/api/usuarios/registro/admin")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.exito").value(true))
            .andExpect(jsonPath("$.timestamp").exists())
            .andExpect(jsonPath("$.mensaje").doesNotExist())
            .andExpect(jsonPath("$.error").doesNotExist())
            .andDo(print());
    }

    @Test
    void debeRetornarForbiddenSinPermisosAdecuadosCliente() throws Exception {
        Usuario request = new Usuario();

        // Datos requeridos como minimo para crear un usuario
        request.setUsername("newadmin");
        request.setPassword("admin123");
        request.setDocumento("1234567890");

        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");

        mockMvc.perform(post("/api/usuarios/registro/admin")
                .with(user("cliente").roles("CLIENTE"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.exito").value(false))
            .andExpect(jsonPath("$.timestamp").exists())
            .andExpect(jsonPath("$.mensaje").value("No tiene permisos para realizar esta acción"))
            .andExpect(jsonPath("$.error").value("Access Denied"))
            .andDo(print());
    }

    @Test
    void debeRetornarForbiddenSinPermisosAdecuadosDoctor() throws Exception {
        Usuario request = new Usuario();

        // Datos requeridos como minimo para crear un usuario
        request.setUsername("newadmin");
        request.setPassword("admin123");
        request.setDocumento("1234567890");

        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");

        mockMvc.perform(post("/api/usuarios/registro/admin")
                .with(user("doctor").roles("DOCTOR"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.exito").value(false))
            .andExpect(jsonPath("$.timestamp").exists())
            .andExpect(jsonPath("$.mensaje").value("No tiene permisos para realizar esta acción"))
            .andExpect(jsonPath("$.error").value("Access Denied"))
            .andDo(print());
    }
    //endregion
}
