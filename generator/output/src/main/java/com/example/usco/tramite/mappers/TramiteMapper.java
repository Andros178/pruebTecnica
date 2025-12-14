package com.example.usco.tramite.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.tramite.Tramite;
import com.example.usco.tramite.dtos.TramiteDTO;

@Mapper(componentModel = "spring")
public interface TramiteMapper {

    TramiteDTO toDTO(Tramite entity);

    Tramite toEntity(TramiteDTO dto);
}
