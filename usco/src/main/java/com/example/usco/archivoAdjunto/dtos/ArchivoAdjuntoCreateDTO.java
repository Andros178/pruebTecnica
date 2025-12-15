package com.example.usco.archivoAdjunto.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArchivoAdjuntoCreateDTO {

    
    private String url;

    
    private String mime;
    private String nombreArchivo;
    private String tamano;
    

}
