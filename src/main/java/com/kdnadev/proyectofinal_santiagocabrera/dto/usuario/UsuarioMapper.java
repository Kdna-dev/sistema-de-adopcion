package com.kdnadev.proyectofinal_santiagocabrera.dto.usuario;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.kdnadev.proyectofinal_santiagocabrera.model.Usuario;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UsuarioMapper {
    UsuarioResponseDTO toDTO(Usuario usuario);
    List<UsuarioResponseDTO> toDTO(List<Usuario> usuarios);

    // Estos campos el usuario no deberia poder editarlos usualmente
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "cantidadMascotasAdoptadas", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "documento", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUsuarioFromDTO(UsuarioUpdateDTO dto, @MappingTarget Usuario usuario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "cantidadMascotasAdoptadas", constant = "0")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    Usuario toEntity(UsuarioCreateDTO dto);
}
