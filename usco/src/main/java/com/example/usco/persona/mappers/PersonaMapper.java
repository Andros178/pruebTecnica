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

    // Do not map tipoIdentificacionId -> tipoIdentificacion.id here, to avoid creating
    // transient TipoIdentificacion instances. The service will resolve and set the
    // association from the repository.
    Persona toEntity(PersonaDTO dto);
}
