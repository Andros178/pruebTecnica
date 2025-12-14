package com.example.usco.comentario.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComentarioDTO {
    private Long id;
    private String mensaje;
    private Long usuarioId;
    private String usuarioNombre;
    private Long estadoId;
    private String estadoNombre;
    private LocalDateTime createdAt;
}
