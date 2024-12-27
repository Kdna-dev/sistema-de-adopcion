package com.kdnadev.proyectofinal_santiagocabrera.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kdnadev.proyectofinal_santiagocabrera.common.response.GenericResponse;
import com.kdnadev.proyectofinal_santiagocabrera.dto.adopcion.AdopcionCreateDTO;
import com.kdnadev.proyectofinal_santiagocabrera.dto.adopcion.AdopcionMapper;
import com.kdnadev.proyectofinal_santiagocabrera.dto.adopcion.AdopcionResponse;
import com.kdnadev.proyectofinal_santiagocabrera.model.Adopcion;
import com.kdnadev.proyectofinal_santiagocabrera.service.AdopcionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/adopciones")
public class AdopcionController {

    private AdopcionService adopcionService;
    private AdopcionMapper adopcionMapper;

    public AdopcionController (AdopcionService adopcionService, AdopcionMapper adopcionMapper){
        this.adopcionService = adopcionService;
        this.adopcionMapper = adopcionMapper;
    }

    @Operation(summary = "Registrar adopcion", description = "Crea el vinculo de adopcion entre la mascota y el usuario", responses = {
        @ApiResponse(responseCode = "201", description = "Adopcion creada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdopcionResponse.class))),
        @ApiResponse(responseCode = "404", description = "Mascota no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "409", description = "Mascota no disponible para adopcion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @PostMapping("/")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN', 'CLIENTE')")
    public ResponseEntity<AdopcionResponse> create(@RequestBody AdopcionCreateDTO adopcion) {
        Adopcion adopcionCreada = adopcionService.create(adopcion);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(adopcionCreada.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(new AdopcionResponse(adopcionMapper.toDTO(adopcionCreada)));
    }


    @Operation(summary = "Eliminar adopcion", description = "Elimina el vinculo de adopcion entre la mascota y el usuario", responses = {
        @ApiResponse(responseCode = "200", description = "Adopcion eliminada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "404", description = "Mascota no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "404", description = "Vinculo de adopcion no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        adopcionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
