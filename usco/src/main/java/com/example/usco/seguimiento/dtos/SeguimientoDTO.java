package com.example.usco.seguimiento.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeguimientoDTO {
    private Long id;
    private LocalDateTime fecha;
    private Long usuarioId;
    private String usuarioNombre;
    private Long estadoId;
    private String estadoNombre;
}
