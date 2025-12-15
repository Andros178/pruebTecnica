package com.example.usco.seguimiento.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.seguimiento.Seguimiento;
import com.example.usco.seguimiento.dtos.SeguimientoDTO;

@Mapper(componentModel = "spring")
public interface SeguimientoMapper {

    @Mapping(target = "fecha", source = "createdAt")
    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "usuarioNombre", source = "usuario.nombre")
    @Mapping(target = "estadoId", source = "estado.id")
    @Mapping(target = "estadoNombre", source = "estado.nombre")
    SeguimientoDTO toDTO(Seguimiento entity);

}
