package com.example.usco.archivoAdjunto.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArchivoAdjuntoDTO {

    private Long id;

    private String mime;
    private String nombreArchivo;
    private java.time.LocalDateTime fechaCreacion;
    private String tamano;
    private Long tramiteId;
    private Long estadoId;
    private String estadoNombre;

}
