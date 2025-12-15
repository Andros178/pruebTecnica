package com.example.usco.tipoDocumento.mappers;

import org.mapstruct.Mapper;

import com.example.usco.tipoDocumento.TipoDocumento;
import com.example.usco.tipoDocumento.dtos.TipoDocumentoDTO;

@Mapper(componentModel = "spring")
public interface TipoDocumentoMapper {
    TipoDocumentoDTO toDTO(TipoDocumento entity);
}
