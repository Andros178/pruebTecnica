package com.example.usco.tramite.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TramiteDTO {

    private Long id;

    private String descripcion;

    private Long asignadoId;
    private String asignadoNombre;

    private Long creadorId;
    private String creadorNombre;

    private Long estadoId;
    private String estadoNombre;

    private Long tipoTramiteId;
    private String tipoTramiteNombre;
    private String tipoTramiteDescripcion;

}
