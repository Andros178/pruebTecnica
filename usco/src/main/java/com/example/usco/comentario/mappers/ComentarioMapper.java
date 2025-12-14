package com.example.usco.comentario.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.comentario.Comentario;
import com.example.usco.comentario.dtos.ComentarioDTO;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {

    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "usuarioNombre", source = "usuario.nombre")
    @Mapping(target = "estadoId", source = "estado.id")
    @Mapping(target = "estadoNombre", source = "estado.nombre")
    ComentarioDTO toDTO(Comentario entity);

}
