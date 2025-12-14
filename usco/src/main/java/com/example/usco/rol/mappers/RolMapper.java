package com.example.usco.rol.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.rol.Rol;
import com.example.usco.rol.dtos.RolDTO;

@Mapper(componentModel = "spring")
public interface RolMapper {
    @Mapping(source = "estado.id", target = "estadoId")
    @Mapping(source = "estado.nombre", target = "estadoNombre")
    RolDTO toDTO(Rol entity);

    @Mapping(source = "estadoId", target = "estado.id")
    Rol toEntity(RolDTO dto);
}
