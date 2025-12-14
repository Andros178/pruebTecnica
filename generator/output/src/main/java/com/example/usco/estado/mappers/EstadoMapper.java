package com.example.usco.estado.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.estado.Estado;
import com.example.usco.estado.dtos.EstadoDTO;

@Mapper(componentModel = "spring")
public interface EstadoMapper {

    EstadoDTO toDTO(Estado entity);

    Estado toEntity(EstadoDTO dto);
}
