package com.example.usco.tipoDocumento.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TipoDocumentoDTO {
    private Long id;
    private String nombre;
}
