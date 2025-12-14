package com.example.usco.rol.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RolDTO {

    private Long id;

    private String nombre;
    private Long estadoId;
    private String estadoNombre;

}
