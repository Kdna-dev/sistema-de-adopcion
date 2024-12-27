package com.kdnadev.proyectofinal_santiagocabrera.dto.adopcion;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.kdnadev.proyectofinal_santiagocabrera.model.Adopcion;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AdopcionMapper {
    AdopcionResponseDTO toDTO(Adopcion adopcion);
    List<AdopcionResponseDTO> toDTO(List<Adopcion> adopcion);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaAdopcion", ignore = true)
    Adopcion toEntity(AdopcionCreateDTO dto);
}
