package com.example.usco.comentario.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.example.usco.comentario.dtos.ComentarioCreateRequest;
import com.example.usco.comentario.dtos.ComentarioDTO;
import com.example.usco.comentario.services.ComentarioService;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
public class TramiteComentarioController {

    private final ComentarioService comentarioService;

    @PostMapping("/api/tramite/{id}/comentarios")
    public ResponseEntity<ComentarioDTO> addComment(@PathVariable Long id, @Valid @RequestBody ComentarioCreateRequest req) {
        var dto = comentarioService.addComment(id, req);
        return ResponseEntity.status(201).body(dto);
    }

    @GetMapping("/api/tramite/{id}/comentarios")
    public ResponseEntity<List<ComentarioDTO>> timeline(@PathVariable Long id) {
        var list = comentarioService.timeline(id);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

}
