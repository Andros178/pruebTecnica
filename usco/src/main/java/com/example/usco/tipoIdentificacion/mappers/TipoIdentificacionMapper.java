package com.example.usco.tipoIdentificacion.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.tipoIdentificacion.TipoIdentificacion;
import com.example.usco.tipoIdentificacion.dtos.TipoIdentificacionDTO;

@Mapper(componentModel = "spring")
public interface TipoIdentificacionMapper {

    @Mapping(target = "estadoId", source = "estado.id")
    TipoIdentificacionDTO toDTO(TipoIdentificacion entity);

    @Mapping(target = "estado.id", source = "estadoId")
    TipoIdentificacion toEntity(TipoIdentificacionDTO dto);
}
