package com.example.usco.comentario.dtos;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComentarioCreateRequest {
    private String mensaje;
    private Long estadoId;
}
