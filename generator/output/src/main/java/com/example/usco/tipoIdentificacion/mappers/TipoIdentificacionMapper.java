package com.example.usco.tipoIdentificacion.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.tipoIdentificacion.TipoIdentificacion;
import com.example.usco.tipoIdentificacion.dtos.TipoIdentificacionDTO;

@Mapper(componentModel = "spring")
public interface TipoIdentificacionMapper {

    TipoIdentificacionDTO toDTO(TipoIdentificacion entity);

    TipoIdentificacion toEntity(TipoIdentificacionDTO dto);
}
