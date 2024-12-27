package com.kdnadev.proyectofinal_santiagocabrera.dto.tipo_mascota;

import java.util.Collections;
import java.util.List;

import com.kdnadev.proyectofinal_santiagocabrera.common.response.GenericResponse;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TipoMascotaResponse")
public class TipoMascotaResponse extends GenericResponse<List<TipoMascotaResponseDTO>> {
    public TipoMascotaResponse(TipoMascotaResponseDTO data){
        super(Collections.singletonList(data));
    }

    public TipoMascotaResponse(List<TipoMascotaResponseDTO> tiposMascota){
        super(tiposMascota);
    }
}
