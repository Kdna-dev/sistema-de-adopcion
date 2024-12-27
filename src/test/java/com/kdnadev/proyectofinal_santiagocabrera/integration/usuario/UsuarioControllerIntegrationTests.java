package com.kdnadev.proyectofinal_santiagocabrera.integration.usuario;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.kdnadev.proyectofinal_santiagocabrera.dto.UsuarioDTO;
import com.kdnadev.proyectofinal_santiagocabrera.dto.usuario.UsuarioCreateDTO;
import com.kdnadev.proyectofinal_santiagocabrera.integration.common.BaseIntegrationTest;
import com.kdnadev.proyectofinal_santiagocabrera.model.Rol;
import com.kdnadev.proyectofinal_santiagocabrera.model.Usuario;
import com.kdnadev.proyectofinal_santiagocabrera.service.UsuarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UsuarioControllerIntegrationTests extends BaseIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {

        // Crear usuarios de prueba
        UsuarioCreateDTO admin = new UsuarioCreateDTO();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        usuarioService.create(admin, Rol.ADMIN.getCodigo());

        UsuarioCreateDTO doctor = new UsuarioCreateDTO();
        doctor.setUsername("doctor");
        doctor.setPassword(passwordEncoder.encode("doctor123"));
        usuarioService.create(doctor, Rol.DOCTOR.getCodigo());

        UsuarioCreateDTO cliente = new UsuarioCreateDTO();
        cliente.setUsername("cliente");
        cliente.setPassword(passwordEncoder.encode("cliente123"));
        usuarioService.create(cliente, Rol.CLIENTE.getCodigo());
    }

    HttpEntity<String> setHeadersAdmin() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("admin", "admin123");
        return new HttpEntity<>(headers);
    }

    HttpEntity<String> setHeadersDoctor() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("doctor", "doctor123");
        return new HttpEntity<>(headers);
    }

    HttpEntity<String> setHeadersCliente() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("cliente", "cliente123");
        return new HttpEntity<>(headers);
    }

    // region GetMethods
    @Test
    void debeRetornarUnauthorizedSinCredenciales() {
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    "/api/usuarios",
                    HttpMethod.GET,
                    new HttpEntity<>(new HttpHeaders()), // Sin credenciales
                    String.class);
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.UNAUTHORIZED, e.getStatusCode());
        }
    }

    @Test
    void debeObtenerTodosLosUsuariosLogueadoAdmin() {
        ResponseEntity<UsuarioDTO[]> responseWithHeaders = restTemplate
                .exchange("/api/usuarios",
                        HttpMethod.GET,
                        setHeadersAdmin(),
                        UsuarioDTO[].class);
        assertEquals(HttpStatus.OK, responseWithHeaders.getStatusCode());
    }

    @Test
    void debeRetornarForbiddenLogueadoDoctor() {
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    "/api/usuarios",
                    HttpMethod.GET,
                    setHeadersDoctor(),
                    String.class);
            assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.FORBIDDEN, e.getStatusCode());
        }
    }

    @Test
    void debeRetornarForbiddenLogueadoCliente() {
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    "/api/usuarios",
                    HttpMethod.GET,
                    setHeadersCliente(),
                    String.class);
            assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.FORBIDDEN, e.getStatusCode());
        }
    }

    @Test
    void debeObtenerUsuarioExstentePorId() {
        ResponseEntity<Usuario> response = restTemplate.getForEntity("/api/usuarios/8", Usuario.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(8);
    }

    @Test
    void intentaObtenerUsuarioInexstentePorId() {
        ResponseEntity<Usuario> response = restTemplate.getForEntity("/api/usuarios/1", Usuario.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
    // endregion

    // region Post Methods
    @Test
    @DirtiesContext
    void debeCrearUnUsuarioAdmin() {
        Usuario usuarioTest = new Usuario();
        usuarioTest.setUsername("Prueba");
        usuarioTest.setPassword("passPrueba");
        usuarioTest.setNombre("Prueba");
        usuarioTest.setEmail("prueba@prueba.com");
        usuarioTest.setDocumento("123-456-0987");
        usuarioTest.setTelefono("123-456-7890");

        ResponseEntity<Usuario> response = restTemplate.postForEntity("/api/usuarios/registro/admin", usuarioTest,
                Usuario.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isNotNull();

        Usuario usuarioCreado = response.getBody();
        assertThat(usuarioCreado).isNotNull();
        assertThat(usuarioCreado.getUsername()).isEqualTo(usuarioTest.getUsername());
        assertThat(usuarioCreado.getPassword()).isEqualTo(usuarioTest.getPassword());
        assertThat(usuarioCreado.getNombre()).isEqualTo(usuarioTest.getNombre());
        assertThat(usuarioCreado.getEmail()).isEqualTo(usuarioTest.getEmail());
        assertThat(usuarioCreado.getDocumento()).isEqualTo(usuarioTest.getDocumento());
        assertThat(usuarioCreado.getTelefono()).isEqualTo(usuarioTest.getTelefono());
        assertThat(usuarioCreado.getRoles()).contains(Rol.ADMIN);
    }

    @Test
    @DirtiesContext
    void intentaCrearUnUsuarioConUsernameDuplicado() {
        Usuario usuarioTest = new Usuario();
        usuarioTest.setUsername("Prueba");
        usuarioTest.setPassword("passPrueba");
        usuarioTest.setNombre("Prueba");
        usuarioTest.setEmail("prueba@prueba.com");
        usuarioTest.setDocumento("123-456-0987");
        usuarioTest.setTelefono("123-456-7890");

        ResponseEntity<Usuario> response = restTemplate.postForEntity("/api/usuarios/registro/admin", usuarioTest,
                Usuario.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    // endregion
}
