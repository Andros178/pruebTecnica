package com.example.usco.tipoTramite.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.tipoTramite.TipoTramite;
import com.example.usco.tipoTramite.dtos.TipoTramiteDTO;

@Mapper(componentModel = "spring")
public interface TipoTramiteMapper {
    @Mapping(target = "estadoId", source = "estado.id")
    @Mapping(target = "estadoNombre", source = "estado.nombre")
    @Mapping(target = "descripcion", source = "descripcion")
    TipoTramiteDTO toDTO(TipoTramite entity);

    @Mapping(source = "estadoId", target = "estado.id")
    TipoTramite toEntity(TipoTramiteDTO dto);
}
