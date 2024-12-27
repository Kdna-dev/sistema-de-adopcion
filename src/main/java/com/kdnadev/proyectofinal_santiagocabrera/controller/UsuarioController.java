package com.kdnadev.proyectofinal_santiagocabrera.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kdnadev.proyectofinal_santiagocabrera.common.response.GenericResponse;
import com.kdnadev.proyectofinal_santiagocabrera.dto.usuario.UsuarioCreateDTO;
import com.kdnadev.proyectofinal_santiagocabrera.dto.usuario.UsuarioMapper;
import com.kdnadev.proyectofinal_santiagocabrera.dto.usuario.UsuarioResponse;
import com.kdnadev.proyectofinal_santiagocabrera.dto.usuario.UsuarioUpdateDTO;
import com.kdnadev.proyectofinal_santiagocabrera.exception.ResourceNotFoundException;
import com.kdnadev.proyectofinal_santiagocabrera.model.Rol;
import com.kdnadev.proyectofinal_santiagocabrera.model.Usuario;
import com.kdnadev.proyectofinal_santiagocabrera.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;
    private UsuarioMapper usuarioMapper;

    public UsuarioController(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @Operation(summary = "Obtener usuarios", description = "Obtiene los datos de todos los usuarios", responses = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponse> getAll() {
        List<Usuario> usuarios = usuarioService.getAll();
        return ResponseEntity.ok(new UsuarioResponse(usuarioMapper.toDTO(usuarios)));
    }

    @Operation(summary = "Obtener usuario", description = "Obtiene los datos de un usuario de cualquier tipo", responses = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    public ResponseEntity<UsuarioResponse> getById(@PathVariable Long id) {
        Usuario usuarioEncontrado = usuarioService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el usuario con id: " + id));

        return ResponseEntity.ok(new UsuarioResponse(usuarioMapper.toDTO(usuarioEncontrado)));
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos de un usuario de cualquier tipo", responses = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponse> update(@PathVariable Long id, @RequestBody UsuarioUpdateDTO usuario) {
        return usuarioService.findById(id)
                .map(usuarioExistente -> {
                    Usuario usuarioActualizado = usuarioService.update(id, usuario);
                    return ResponseEntity.ok(new UsuarioResponse(usuarioMapper.toDTO(usuarioActualizado)));
                })
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el usuario con el id: " + id));
    }

    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario de cualquier tipo", responses = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Registrar Admin", description = "Crea un usuario con permisos admin", responses = {
        @ApiResponse(responseCode = "201", description = "Usuario creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
        @ApiResponse(responseCode = "409", description = "Usuario ya existente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "409", description = "Documento ya ingresado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos faltantes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @PostMapping("/registro/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponse> registrarAdmin(@RequestBody UsuarioCreateDTO usuario) {
        Usuario usuarioCreado = usuarioService.create(usuario, Rol.ADMIN.getCodigo());
        URI location = generarUriUsuario(usuarioCreado.getId());

        return ResponseEntity
                .created(location)
                .body(new UsuarioResponse(usuarioMapper.toDTO(usuarioCreado)));
    }

    @Operation(summary = "Registrar Doctor", description = "Crea un usuario con permisos doctor", responses = {
        @ApiResponse(responseCode = "201", description = "Usuario creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
        @ApiResponse(responseCode = "409", description = "Usuario ya existente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "409", description = "Documento ya ingresado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos faltantes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @PostMapping("/registro/doctor")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponse> registrarDoctor(@RequestBody UsuarioCreateDTO usuario) {
        Usuario usuarioCreado = usuarioService.create(usuario, Rol.DOCTOR.getCodigo());
        URI location = generarUriUsuario(usuarioCreado.getId());

        return ResponseEntity
                .created(location)
                .body(new UsuarioResponse(usuarioMapper.toDTO(usuarioCreado)));
    }

    @Operation(summary = "Registrar Cliente", description = "Crea un usuario con permisos cliente", responses = {
        @ApiResponse(responseCode = "201", description = "Usuario creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
        @ApiResponse(responseCode = "409", description = "Usuario ya existente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "409", description = "Documento ya ingresado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos faltantes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @PostMapping("/registro/cliente")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    public ResponseEntity<UsuarioResponse> registrarCliente(@RequestBody UsuarioCreateDTO usuario) {
        Usuario usuarioCreado = usuarioService.create(usuario, Rol.CLIENTE.getCodigo());
        URI location = generarUriUsuario(usuarioCreado.getId());

        return ResponseEntity
                .created(location)
                .body(new UsuarioResponse(usuarioMapper.toDTO(usuarioCreado)));
    }

    /**
     * Genera la URI de un usuario para devolver en el endpoint
     * @param id
     * @return
     */
    private URI generarUriUsuario(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/usuarios/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
