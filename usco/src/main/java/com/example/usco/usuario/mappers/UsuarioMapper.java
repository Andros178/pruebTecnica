package com.example.usco.usuario.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.usuario.Usuario;
import com.example.usco.usuario.dtos.UsuarioDTO;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "personaId", source = "persona.id")
    @Mapping(target = "personaNombre", source = "persona.nombre")
    @Mapping(target = "estadoId", source = "estado.id")
    UsuarioDTO toDTO(Usuario entity);

    @Mapping(source = "personaId", target = "persona.id")
    @Mapping(source = "personaNombre", target = "persona.nombre")
    @Mapping(source = "estadoId", target = "estado.id")
    Usuario toEntity(UsuarioDTO dto);
}
