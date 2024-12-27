package com.kdnadev.proyectofinal_santiagocabrera.dto.tipo_mascota;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.kdnadev.proyectofinal_santiagocabrera.model.TipoMascota;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TipoMascotaMapper {
    TipoMascotaResponseDTO toDTO(TipoMascota mascota);

    List<TipoMascotaResponseDTO> toDTO(List<TipoMascota> mascotas);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    TipoMascota toEntity(TipoMascotaCreateDTO dto);
}
