package com.example.usco.rol.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.usco.rol.Rol;
import com.example.usco.rol.dtos.RolDTO;

@Mapper(componentModel = "spring")
public interface RolMapper {

    RolDTO toDTO(Rol entity);

    Rol toEntity(RolDTO dto);
}
