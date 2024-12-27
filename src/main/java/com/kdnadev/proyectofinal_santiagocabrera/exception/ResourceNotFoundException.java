package com.kdnadev.proyectofinal_santiagocabrera.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){
        super(message);
    }
}
