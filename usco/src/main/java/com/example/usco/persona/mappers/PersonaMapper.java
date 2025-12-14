package com.example.usco.persona.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.persona.Persona;
import com.example.usco.persona.dtos.PersonaDTO;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    @Mapping(target = "tipoIdentificacionNombre", source = "tipoIdentificacion.nombre")
    @Mapping(target = "estadoId", source = "estado.id")
    @Mapping(target = "estadoNombre", source = "estado.nombre")
    PersonaDTO toDTO(Persona entity);

    @Mapping(source = "tipoIdentificacionId", target = "tipoIdentificacion.id")
    @Mapping(source = "estadoId", target = "estado.id")
    Persona toEntity(PersonaDTO dto);
}
