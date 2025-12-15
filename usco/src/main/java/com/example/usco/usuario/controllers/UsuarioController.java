package com.example.usco.usuario.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.example.usco.usuario.dtos.UsuarioDTO;
import com.example.usco.usuario.services.UsuarioService;
import com.example.usco.utils.UriBuilderUtil;
import com.example.usco.usuarioRol.services.UsuarioRolService;
import com.example.usco.rol.dtos.RolDTO;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UriBuilderUtil uriBuilderUtil;
    private final UsuarioRolService usuarioRolService;

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> findAll(
            @PageableDefault Pageable pageable) {

        Page<UsuarioDTO> page = service.findAll(pageable);
        if (page.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        return service.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody UsuarioDTO dto,
            UriComponentsBuilder ucb) {

        UsuarioDTO saved = service.create(dto);
        URI location = uriBuilderUtil.buildUsuarioUri(saved.getId(), ucb);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioDTO dto) {

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

    @GetMapping("/{id}/roles")
    public ResponseEntity<List<RolDTO>> getRolesByUsuario(@PathVariable Long id) {
        var roles = usuarioRolService.findRolesByUsuario(id);
        if (roles == null || roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/role/{rolNombre}")
    public ResponseEntity<java.util.List<com.example.usco.usuario.dtos.UsuarioDTO>> getUsuariosByRole(@PathVariable String rolNombre) {
        var users = usuarioRolService.findUsuariosByRol(rolNombre);
        if (users == null || users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
}

