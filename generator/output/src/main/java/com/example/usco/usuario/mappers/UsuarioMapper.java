package com.example.usco.usuario.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.usuario.Usuario;
import com.example.usco.usuario.dtos.UsuarioDTO;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuario entity);

    Usuario toEntity(UsuarioDTO dto);
}
