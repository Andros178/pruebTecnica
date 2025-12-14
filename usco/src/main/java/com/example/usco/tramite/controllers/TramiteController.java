package com.example.usco.tramite.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.example.usco.tramite.dtos.TramiteDTO;
import com.example.usco.tramite.dtos.TramiteCreateRequest;
import com.example.usco.tramite.services.TramiteService;
import com.example.usco.utils.UriBuilderUtil;

@RestController
@RequestMapping("/api/tramite")
@RequiredArgsConstructor
public class TramiteController {

    private final TramiteService service;
    private final UriBuilderUtil uriBuilderUtil;

    @GetMapping
    public ResponseEntity<Page<TramiteDTO>> findAll(
            @PageableDefault Pageable pageable) {

        Page<TramiteDTO> page = service.findAll(pageable);
        if (page.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TramiteDTO> findById(@PathVariable Long id) {
        return service.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody TramiteCreateRequest req,
            UriComponentsBuilder ucb) {

        TramiteDTO saved = service.radicar(req);
        URI location = uriBuilderUtil.buildTramiteUri(saved.getId(), ucb);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @Valid @RequestBody TramiteDTO dto) {

        service.update(id, dto);
        return ResponseEntity.noContent().build();
    }

        @PutMapping("/{id}/estado/{estadoId}")
            @PreAuthorize("hasAnyRole('FUNCIONARIO','ADMINISTRATIVO')")
            public ResponseEntity<Void> updateEstado(
                    @PathVariable Long id,
                    @PathVariable Long estadoId) {

                service.updateEstado(id, estadoId);
                return ResponseEntity.noContent().build();
            }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
