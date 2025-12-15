package com.example.usco.documentoTipoTramite.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.usco.documentoTipoTramite.services.DocumentoTipoTramiteService;
import com.example.usco.tipoDocumento.dtos.TipoDocumentoDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tipoTramite")
public class DocumentoTipoTramiteController {

    private final DocumentoTipoTramiteService service;

    @GetMapping("/{id}/documentos-requeridos")
    public ResponseEntity<List<TipoDocumentoDTO>> required(@PathVariable Long id) {
        var list = service.findRequiredByTipoTramite(id);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }
}
