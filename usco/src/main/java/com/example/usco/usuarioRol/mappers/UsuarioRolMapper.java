package com.example.usco.usuarioRol.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.usuarioRol.UsuarioRol;
import com.example.usco.usuarioRol.dtos.UsuarioRolDTO;

@Mapper(componentModel = "spring")
public interface UsuarioRolMapper {

    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "rolId", source = "rol.id")
    @Mapping(target = "estadoId", source = "estado.id")
    @Mapping(target = "estadoNombre", source = "estado.nombre")
    UsuarioRolDTO toDTO(UsuarioRol entity);

    @Mapping(source = "usuarioId", target = "usuario.id")
    @Mapping(source = "rolId", target = "rol.id")
    @Mapping(source = "estadoId", target = "estado.id")
    UsuarioRol toEntity(UsuarioRolDTO dto);
}
