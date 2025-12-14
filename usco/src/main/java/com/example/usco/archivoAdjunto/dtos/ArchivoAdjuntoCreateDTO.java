package com.example.usco.archivoAdjunto.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArchivoAdjuntoCreateDTO {

    // Simulated file URL
    private String url;

    // Optional metadata
    private String mime;
    private String nombreArchivo;
    private String tamano;

}
