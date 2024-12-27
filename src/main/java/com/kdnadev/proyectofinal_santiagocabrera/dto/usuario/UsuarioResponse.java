package com.kdnadev.proyectofinal_santiagocabrera.dto.usuario;

import java.util.Collections;
import java.util.List;

import com.kdnadev.proyectofinal_santiagocabrera.common.response.GenericResponse;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UsuarioResponse")
public class UsuarioResponse extends GenericResponse<List<UsuarioResponseDTO>>{
    public UsuarioResponse(UsuarioResponseDTO usuario){
        super(Collections.singletonList(usuario));
    }

    public UsuarioResponse(List<UsuarioResponseDTO> usuarios){
        super(usuarios);
    }
}
