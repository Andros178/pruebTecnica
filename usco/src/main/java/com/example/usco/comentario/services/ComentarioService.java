package com.example.usco.comentario.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.RequiredArgsConstructor;

import com.example.usco.comentario.Comentario;
import com.example.usco.comentario.dtos.ComentarioCreateRequest;
import com.example.usco.comentario.dtos.ComentarioDTO;
import com.example.usco.comentario.mappers.ComentarioMapper;
import com.example.usco.comentario.repositories.ComentarioRepository;
import com.example.usco.tramite.repositories.TramiteRepository;
import com.example.usco.estado.Estado;
import com.example.usco.usuario.Usuario;
import com.example.usco.usuario.repositories.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;
    private final TramiteRepository tramiteRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public ComentarioDTO addComment(Long tramiteId, ComentarioCreateRequest req) {
        var tramite = tramiteRepository.findById(tramiteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tramite no encontrado"));

        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No autenticado");
        }

        var nombre = auth.getName();
        var usuario = usuarioRepository.findByNombre(nombre)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no encontrado"));

        
        boolean allowed = false;
        if (tramite.getCreador() != null && tramite.getCreador().getId() != null && tramite.getCreador().getId().equals(usuario.getId())) {
            allowed = true;
        }
        if (tramite.getAsignado() != null && tramite.getAsignado().getId() != null && tramite.getAsignado().getId().equals(usuario.getId())) {
            allowed = true;
        }
        var authorities = auth.getAuthorities();
        if (authorities != null) {
            var it = authorities.stream().map(a -> a.getAuthority()).collect(Collectors.toList());
            if (it.contains("ROLE_FUNCIONARIO") || it.contains("ROLE_ADMINISTRATIVO")) {
                allowed = true;
            }
        }

        if (!allowed) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No autorizado a comentar este tramite");
        }

        Comentario c = Comentario.builder()
            .mensaje(req.getMensaje())
            .tramite(tramite)
            .usuario(usuario)
            .build();

        if (req.getEstadoId() != null) {
            Estado e = new Estado();
            e.setId(req.getEstadoId());
            c.setEstado(e);
        }

        var saved = comentarioRepository.save(c);
        return comentarioMapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<ComentarioDTO> timeline(Long tramiteId) {
        if (!tramiteRepository.existsById(tramiteId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tramite no encontrado");
        }

        return comentarioRepository.findAllByTramite_IdOrderByCreatedAtAsc(tramiteId)
            .stream()
            .map(comentarioMapper::toDTO)
            .collect(Collectors.toList());
    }

}
