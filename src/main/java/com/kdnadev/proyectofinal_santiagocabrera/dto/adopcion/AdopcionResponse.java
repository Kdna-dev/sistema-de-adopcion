package com.kdnadev.proyectofinal_santiagocabrera.dto.adopcion;

import java.util.Collections;
import java.util.List;

import com.kdnadev.proyectofinal_santiagocabrera.common.response.GenericResponse;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AdopcionResponse")
public class AdopcionResponse extends GenericResponse<List<AdopcionResponseDTO>>{
    public AdopcionResponse(AdopcionResponseDTO usuario){
        super(Collections.singletonList(usuario));
    }

    public AdopcionResponse(List<AdopcionResponseDTO> usuarios){
        super(usuarios);
    }
}

