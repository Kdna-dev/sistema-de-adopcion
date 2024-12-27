package com.kdnadev.proyectofinal_santiagocabrera.dto.mascota;

import java.util.Collections;
import java.util.List;

import com.kdnadev.proyectofinal_santiagocabrera.common.response.GenericResponse;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "MascotaResponse")
public class MascotaResponse extends GenericResponse<List<MascotaResponseDTO>> {
    public MascotaResponse(MascotaResponseDTO data){
        super(Collections.singletonList(data));
    }

    public MascotaResponse(List<MascotaResponseDTO> mascotas){
        super(mascotas);
    }
}
