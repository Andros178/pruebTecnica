package com.example.usco.archivoAdjunto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.archivoAdjunto.ArchivoAdjunto;
import com.example.usco.archivoAdjunto.dtos.ArchivoAdjuntoDTO;

@Mapper(componentModel = "spring")
public interface ArchivoAdjuntoMapper {

    ArchivoAdjuntoDTO toDTO(ArchivoAdjunto entity);

    ArchivoAdjunto toEntity(ArchivoAdjuntoDTO dto);
}
