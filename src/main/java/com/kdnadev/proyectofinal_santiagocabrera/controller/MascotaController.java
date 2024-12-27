package com.kdnadev.proyectofinal_santiagocabrera.controller;

import com.kdnadev.proyectofinal_santiagocabrera.common.response.GenericResponse;
import com.kdnadev.proyectofinal_santiagocabrera.dto.mascota.MascotaCreateDTO;
import com.kdnadev.proyectofinal_santiagocabrera.dto.mascota.MascotaMapper;
import com.kdnadev.proyectofinal_santiagocabrera.dto.mascota.MascotaResponse;
import com.kdnadev.proyectofinal_santiagocabrera.dto.mascota.MascotaUpdateDTO;
import com.kdnadev.proyectofinal_santiagocabrera.dto.tipo_mascota.TipoMascotaCreateDTO;
import com.kdnadev.proyectofinal_santiagocabrera.dto.tipo_mascota.TipoMascotaMapper;
import com.kdnadev.proyectofinal_santiagocabrera.dto.tipo_mascota.TipoMascotaResponse;
import com.kdnadev.proyectofinal_santiagocabrera.exception.ResourceNotFoundException;
import com.kdnadev.proyectofinal_santiagocabrera.model.Mascota;
import com.kdnadev.proyectofinal_santiagocabrera.model.TipoMascota;
import com.kdnadev.proyectofinal_santiagocabrera.service.MascotaService;

import java.net.URI;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/mascotas")
@Tag(name = "Mascotas", description = "Controlador para gestionar mascotas y tipos de mascotas")
public class MascotaController {
    private MascotaService mascotaService;
    private MascotaMapper mascotaMapper;
    private TipoMascotaMapper tipoMascotaMapper;

    public MascotaController(MascotaService mascotaService, MascotaMapper mascotaMapper, TipoMascotaMapper tipoMascotaMapper) {
        this.mascotaService = mascotaService;
        this.mascotaMapper = mascotaMapper;
        this.tipoMascotaMapper = tipoMascotaMapper;
    }

    @Operation(summary = "Obtener todas las mascotass", description = "Retorna una lista de todas las mascotas", responses = {
        @ApiResponse(responseCode = "200", description = "Lista total de mascotas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MascotaResponse.class))),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    public ResponseEntity<MascotaResponse> getAll() {
        List<Mascota> mascotas = mascotaService.getAll();
        return ResponseEntity.ok(new MascotaResponse(mascotaMapper.toDTO(mascotas)));
    }

    @Operation(summary = "Obtener mascota", description = "Retorna la informacion de la mascota solicitada por id en un array de 1 elemento", responses = {
            @ApiResponse(responseCode = "200", description = "Mascota encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MascotaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
            @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    public ResponseEntity<MascotaResponse> getById(@PathVariable Long id) {
        Mascota mascota = mascotaService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la mascota con id: " + id));

        return ResponseEntity.ok(new MascotaResponse(mascotaMapper.toDTO(mascota)));
    }

    @Operation(summary = "Obtener todas las mascotass", description = "Retorna una lista de todas las mascotas", responses = {
        @ApiResponse(responseCode = "200", description = "Lista total de mascotas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MascotaResponse.class))),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @GetMapping("/disponibles")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN', 'CLIENTE')")
    public ResponseEntity<MascotaResponse> getAllDisponiblesParaAdopcion() {
        List<Mascota> mascotas = mascotaService.getAllDisponiblesParaAdopcion();
        return ResponseEntity.ok(new MascotaResponse(mascotaMapper.toDTO(mascotas)));
    }

    @Operation(summary = "Obtener mascota", description = "Retorna la informacion de la mascota solicitada por id en un array de 1 elemento", responses = {
            @ApiResponse(responseCode = "200", description = "Mascota encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MascotaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
            @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @GetMapping("/disponibles/{id}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN', 'CLIENTE')")
    public ResponseEntity<MascotaResponse> getByIdDisponibleAdopcion(@PathVariable Long id) {
        Mascota mascota = mascotaService.getByIdDisponibleAdopcion(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la mascota con id: " + id + ", o no esta disponible para adopcion."));

        return ResponseEntity.ok(new MascotaResponse(mascotaMapper.toDTO(mascota)));
    }

    @Operation(summary = "Crear mascota", description = "Crea una mascota en la base de datos", responses = {
        @ApiResponse(responseCode = "201", description = "Mascota creada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MascotaResponse.class))),
        @ApiResponse(responseCode = "404", description = "Tipo mascota no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    public ResponseEntity<MascotaResponse> create(@RequestBody MascotaCreateDTO mascota) {
        Mascota mascotaCreada = mascotaService.create(mascota);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(mascotaCreada.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(new MascotaResponse(mascotaMapper.toDTO(mascotaCreada)));
    }

    @Operation(summary = "Actualizar mascota", description = "Actualiza la informacion de la mascota proporcionada por id", responses = {
        @ApiResponse(responseCode = "200", description = "Mascota actualizada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MascotaResponse.class))),
        @ApiResponse(responseCode = "404", description = "Mascota no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "404", description = "Tipo mascota no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    public ResponseEntity<MascotaResponse> update(@PathVariable Long id, @RequestBody MascotaUpdateDTO mascota) {
        Mascota mascotaActualizada = mascotaService.update(id, mascota);
        return ResponseEntity.ok(new MascotaResponse(mascotaMapper.toDTO(mascotaActualizada)));
    }

    @Operation(summary = "Eliminar mascota", description = "Elimina la informacion de la mascota proporcionada por id", responses = {
        @ApiResponse(responseCode = "204", description = "Mascota eliminada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MascotaResponse.class))),
        @ApiResponse(responseCode = "404", description = "Mascota no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        mascotaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar estado mascota", description = "Actualiza la disponibilidad de la mascota proporcionada por id", responses = {
        @ApiResponse(responseCode = "200", description = "Mascota actualizada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MascotaResponse.class))),
        @ApiResponse(responseCode = "404", description = "Mascota no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @PutMapping("setDisponible/{id}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    public ResponseEntity<MascotaResponse> setDisponibleParaAdopcion(@PathVariable Long id,
            @RequestParam boolean disponible) {
        Mascota mascotaActualizada = mascotaService.setDisponibleParaAdopcion(id, disponible);
        return ResponseEntity.ok(new MascotaResponse(mascotaMapper.toDTO(mascotaActualizada)));
    }

    /**
     * Metodo para crear los tipos de mascota
     * @param tipo
     * @return Devuelve status created sin URI, ya que no disponemos de un metodo de consulta por id
     */
    @Operation(summary = "Crear tipo mascota", description = "Crea un tipo mascota en la base de datos", responses = {
        @ApiResponse(responseCode = "201", description = "Tipo mascota creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoMascotaResponse.class))),
        @ApiResponse(responseCode = "409", description = "Tipo mascota ya existente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @PostMapping("/tipoMascota")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipoMascotaResponse> createTipoMascota(@RequestBody TipoMascotaCreateDTO tipo) {
        TipoMascota tipoCreado = mascotaService.createTipoMascota(tipo);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new TipoMascotaResponse(tipoMascotaMapper.toDTO(tipoCreado)));
    }


    @Operation(summary = "Obtiener tipos mascota", description = "Obtiene la lista de todos los tipo mascota", responses = {
        @ApiResponse(responseCode = "200", description = "Tipos mascota encontrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoMascotaResponse.class))),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para realizar la accion", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class))),
    })
    @GetMapping("/tipoMascota")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    public ResponseEntity<TipoMascotaResponse> getAllTipoMascota() {
        List<TipoMascota> tipoMascotas = mascotaService.getAllTipoMascota();
        return ResponseEntity.ok(new TipoMascotaResponse(tipoMascotaMapper.toDTO(tipoMascotas)));
    }
}
