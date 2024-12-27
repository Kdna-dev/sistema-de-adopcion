package com.kdnadev.proyectofinal_santiagocabrera.dto.mascota;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.kdnadev.proyectofinal_santiagocabrera.model.Mascota;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface MascotaMapper {
    MascotaResponseDTO toDTO(Mascota mascota);
    List<MascotaResponseDTO> toDTO(List<Mascota> mascotas);

    // Estos campos el Mascota no deberia poder editarlos usualmente
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMascotaFromDTO(MascotaUpdateDTO dto, @MappingTarget Mascota mascota);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Mascota toEntity(MascotaCreateDTO dto);
}
