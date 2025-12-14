package com.example.usco.usuario.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {

    private Long id;

    private String nombre;
    private String contrasena;
    private Long personaId;
    private Long estadoId;
    private String personaNombre;

}
