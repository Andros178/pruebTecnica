package com.example.usco.persona.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.persona.Persona;
import com.example.usco.persona.dtos.PersonaDTO;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    PersonaDTO toDTO(Persona entity);

    Persona toEntity(PersonaDTO dto);
}
