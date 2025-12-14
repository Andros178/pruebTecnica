package com.example.usco.archivoAdjunto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.archivoAdjunto.ArchivoAdjunto;
import com.example.usco.archivoAdjunto.dtos.ArchivoAdjuntoDTO;

@Mapper(componentModel = "spring")
public interface ArchivoAdjuntoMapper {

    @Mapping(target= "tramiteId", source="tramite.id")
    @Mapping(target = "estadoId", source = "estado.id")
    @Mapping(target = "estadoNombre", source = "estado.nombre")
    ArchivoAdjuntoDTO toDTO(ArchivoAdjunto entity);

    @Mapping(source = "tramiteId", target = "tramite.id")
    @Mapping(source= "estadoId", target = "estado.id")
    ArchivoAdjunto toEntity(ArchivoAdjuntoDTO dto);
}
