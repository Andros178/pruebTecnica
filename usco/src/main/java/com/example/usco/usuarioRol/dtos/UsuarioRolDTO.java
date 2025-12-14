package com.example.usco.usuarioRol.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioRolDTO {

    private Long id;

    private Long usuarioId;
    private Long rolId;
    private Long estadoId;
    private String estadoNombre;


}
