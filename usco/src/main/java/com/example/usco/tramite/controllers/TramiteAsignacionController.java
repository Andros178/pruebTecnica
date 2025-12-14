package com.example.usco.tramite.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.example.usco.tramite.services.TramiteService;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
public class TramiteAsignacionController {

    private final TramiteService tramiteService;

    public static record AsignarRequest(Long usuarioId) {}

    @PostMapping("/api/tramites/{id}/asignar")
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('FUNCIONARIO')")
    public ResponseEntity<Void> asignar(@PathVariable Long id, @Valid @RequestBody AsignarRequest req) {
        tramiteService.asignar(id, req.usuarioId());
        return ResponseEntity.noContent().build();
    }
}
