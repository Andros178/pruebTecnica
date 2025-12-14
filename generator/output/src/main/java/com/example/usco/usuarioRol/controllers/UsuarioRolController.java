package com.example.usco.usuarioRol.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.example.usco.usuarioRol.dtos.UsuarioRolDTO;
import com.example.usco.usuarioRol.services.UsuarioRolService;
import com.example.usco.utils.UriBuilderUtil;

@RestController
@RequestMapping("/api/usuarioRol")
@RequiredArgsConstructor
public class UsuarioRolController {

    private final UsuarioRolService service;
    private final UriBuilderUtil uriBuilderUtil;

    @GetMapping
    public ResponseEntity<Page<UsuarioRolDTO>> findAll(
            @PageableDefault Pageable pageable) {

        Page<UsuarioRolDTO> page = service.findAll(pageable);
        if (page.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRolDTO> findById(@PathVariable Long id) {
        return service.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody UsuarioRolDTO dto,
            UriComponentsBuilder ucb) {

        UsuarioRolDTO saved = service.create(dto);
        URI location = uriBuilderUtil.buildUsuarioRolUri(saved.getId(), ucb);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRolDTO dto) {

        service.update(id, dto);
        return ResponseEntity.noContent().build();
    }

        @PutMapping("/{id}/estado/{estadoId}")
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
