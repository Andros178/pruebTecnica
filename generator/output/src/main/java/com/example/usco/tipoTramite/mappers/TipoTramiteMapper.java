package com.example.usco.tipoTramite.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.tipoTramite.TipoTramite;
import com.example.usco.tipoTramite.dtos.TipoTramiteDTO;

@Mapper(componentModel = "spring")
public interface TipoTramiteMapper {

    TipoTramiteDTO toDTO(TipoTramite entity);

    TipoTramite toEntity(TipoTramiteDTO dto);
}
