package com.kdnadev.proyectofinal_santiagocabrera.common.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * GenericResponse es un contenedor para respuestas de la API,
 * diseñado para encapsular datos, mensajes, errores y metadatos.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GenericResponse<T> {
    @Schema(description = "Datos de la respuesta", nullable = true)
    private T data;

    @Schema(description = "Mensaje personalizado del error", nullable = true)
    private String mensaje;

    @Schema(description = "Mensaje de error generico", nullable = true)
    private String error;

    @Schema(description = "Fecha y hora de la respuesta")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    @Schema(description = "Indica si la operacion fue exitosa")
    private boolean exito;

    // Constructor para respuestas exitosas
    public GenericResponse(T data) {
        this.data = data;
        this.exito = true;
        this.timestamp = LocalDateTime.now();
    }

    // Constructor para respuestas de error
    public GenericResponse(T data, String mensaje, String error) {
        this.data = data;
        this.mensaje = mensaje;
        this.error = error;
        this.exito = false;
        this.timestamp = LocalDateTime.now();
    }

    // Getters y setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

}