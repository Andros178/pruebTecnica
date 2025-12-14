package com.example.usco.usuarioRol.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.usuarioRol.UsuarioRol;
import com.example.usco.usuarioRol.dtos.UsuarioRolDTO;

@Mapper(componentModel = "spring")
public interface UsuarioRolMapper {

    UsuarioRolDTO toDTO(UsuarioRol entity);

    UsuarioRol toEntity(UsuarioRolDTO dto);
}
