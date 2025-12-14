package com.example.usco.tramite.dtos;

import java.util.List;

import com.example.usco.archivoAdjunto.dtos.ArchivoAdjuntoCreateDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TramiteCreateRequest {

    private Long tipoTramiteId;
    private String descripcion;

    private List<ArchivoAdjuntoCreateDTO> archivos;

}
