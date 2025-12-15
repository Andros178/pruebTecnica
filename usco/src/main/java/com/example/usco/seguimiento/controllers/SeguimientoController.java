package com.example.usco.seguimiento.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.example.usco.seguimiento.dtos.SeguimientoDTO;
import com.example.usco.seguimiento.services.SeguimientoService;

@RestController
@RequiredArgsConstructor
public class SeguimientoController {

    private final SeguimientoService seguimientoService;

    @GetMapping("/api/tramites/{id}/seguimiento")
    public ResponseEntity<List<SeguimientoDTO>> timeline(@PathVariable Long id) {
        var list = seguimientoService.timeline(id);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

}
