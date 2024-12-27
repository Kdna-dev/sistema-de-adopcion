package com.kdnadev.proyectofinal_santiagocabrera.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kdnadev.proyectofinal_santiagocabrera.common.response.GenericResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<GenericResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        GenericResponse<String> response = new GenericResponse<>(
            null,  // data es null porque es un error
            ex.getMessage(),  // mensaje de error que obtenemos de la excepción
            "Validación fallida."  // tipo de error
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<GenericResponse<String>> handleAccessDeniedException(AccessDeniedException ex) {
        GenericResponse<String> response = new GenericResponse<>(
            null,
            "No tiene permisos para realizar esta acción",
            "Acceso denegado."
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GenericResponse<String>> handleResourceNotFoundException(ResourceNotFoundException ex){
        GenericResponse<String> response = new GenericResponse<>(
            null,
            ex.getMessage(),
            "No se encontro el elemento."
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ElementoDuplicadoException.class)
    public ResponseEntity<GenericResponse<String>> handleElementoDuplicadoException(ElementoDuplicadoException ex){
        GenericResponse<String> response = new GenericResponse<>(
            null,
            ex.getMessage(),
            "No se realizo el registro."
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(ValidacionNegocioException.class)
    public ResponseEntity<GenericResponse<String>> handleValidacionNegocioException(ValidacionNegocioException ex){
        GenericResponse<String> response = new GenericResponse<>(
            null,
            ex.getMessage(),
            "No se pudo procesar la solicitud."
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }
}