package com.example.usco.tramite.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.tramite.Tramite;
import com.example.usco.tramite.dtos.TramiteDTO;

@Mapper(componentModel = "spring")
public interface TramiteMapper {

    @Mapping(target = "asignadoId", source = "asignado.id")
    @Mapping(target = "asignadoNombre", source = "asignado.nombre")
    @Mapping(target = "creadorId", source = "creador.id")
    @Mapping(target = "creadorNombre", source = "creador.nombre")
    @Mapping(target = "estadoId", source = "estado.id")
    @Mapping(target = "estadoNombre", source = "estado.nombre")
    @Mapping(target = "tipoTramiteId", source = "tipoTramite.id")
    @Mapping(target = "tipoTramiteNombre", source = "tipoTramite.nombre")
    @Mapping(target = "tipoTramiteDescripcion", source = "tipoTramite.descripcion")
    TramiteDTO toDTO(Tramite entity);

    @Mapping(target = "asignado.id", source = "asignadoId")
    @Mapping(target = "creador.id", source = "creadorId")
    @Mapping(target = "estado.id", source = "estadoId")
    @Mapping(target = "tipoTramite.id", source ="tipoTramiteId")
    Tramite toEntity(TramiteDTO dto);
}
