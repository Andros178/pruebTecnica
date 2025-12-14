package com.example.usco.persona.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonaDTO {

    private Long id;

    private String nombre;
    private String segundoNombre;
    private String apellido;
    private String segundoApellido;
    private String correo;
    private String numeroIdentificacion;
    private Long tipoIdentificacionId;
    private String tipoIdentificacionNombre;
    private Long estadoId;
    private String estadoNombre;

}
