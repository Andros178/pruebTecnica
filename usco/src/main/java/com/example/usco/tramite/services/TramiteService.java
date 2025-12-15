package com.example.usco.tramite.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.usco.tramite.dtos.TramiteDTO;
import com.example.usco.tramite.Tramite;
import com.example.usco.tramite.dtos.TramiteCreateRequest;
import com.example.usco.tramite.mappers.TramiteMapper;
import com.example.usco.tramite.repositories.TramiteRepository;
import com.example.usco.usuario.Usuario;
import com.example.usco.usuario.repositories.UsuarioRepository;
import com.example.usco.archivoAdjunto.repositories.ArchivoAdjuntoRepository;
import com.example.usco.estado.Estado;
import com.example.usco.archivoAdjunto.ArchivoAdjunto;
import com.example.usco.tipoTramite.TipoTramite;
import com.example.usco.tipoTramite.repositories.TipoTramiteRepository;
import com.example.usco.seguimiento.Seguimiento;
import com.example.usco.seguimiento.repositories.SeguimientoRepository;
import com.example.usco.documentoTipoTramite.services.DocumentoTipoTramiteService;
import com.example.usco.tipoDocumento.TipoDocumento;

@Service
@RequiredArgsConstructor
public class TramiteService {

    private final TramiteRepository repository;
    private final TramiteMapper mapper;
    private final ArchivoAdjuntoRepository archivoAdjuntoRepository;
    private final TipoTramiteRepository tipoTramiteRepository;
    private final UsuarioRepository usuarioRepository;
    private final SeguimientoRepository seguimientoRepository;
    private final DocumentoTipoTramiteService documentoTipoTramiteService;

    public Page<TramiteDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Page<TramiteDTO> findByFuncionario(Long usuarioId, Pageable pageable) {
        return repository.findAllByAsignado_Id(usuarioId, pageable).map(mapper::toDTO);
    }

    public Optional<TramiteDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public TramiteDTO create(TramiteDTO dto) {
        dto.setId(null);
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public void update(Long id, TramiteDTO dto) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tramite no encontrado"));

        dto.setId(id);
        repository.save(mapper.toEntity(dto));
    }

    public void delete(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tramite no encontrado"));

        repository.deleteById(id);
    }

    public void updateEstado(Long id, Long estadoId) {
        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tramite no encontrado"));

        Estado nuevoEstado = new Estado();
        nuevoEstado.setId(estadoId);
        entity.setEstado(nuevoEstado);

        repository.save(entity);

        
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getName() != null) {
            var nombre = auth.getName();
            usuarioRepository.findByNombre(nombre).ifPresent(u -> {
                Seguimiento s = Seguimiento.builder()
                    .tramite(entity)
                    .usuario(u)
                    .estado(nuevoEstado)
                    .build();
                seguimientoRepository.save(s);
            });
        }
    }

    public void asignar(Long tramiteId, Long usuarioId) {
        var entity = repository.findById(tramiteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tramite no encontrado"));

        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario a asignar no existe");
        }

        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        entity.setAsignado(usuario);

        repository.save(entity);
    }

    @Transactional
    public TramiteDTO radicar(TramiteCreateRequest req) {

        var tipo = tipoTramiteRepository.findById(req.getTipoTramiteId())
                .orElseThrow(() -> new RuntimeException("TipoTramite no encontrado"));

        var tramite = new Tramite();
        tramite.setDescripcion(req.getDescripcion());
        tramite.setTipoTramite(tipo);

        
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getName() != null) {
            var nombre = auth.getName();
            var usuario = usuarioRepository.findByNombre(nombre)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario creador no encontrado"));
            tramite.setCreador(usuario);
        }

        
        var required = documentoTipoTramiteService.findRequiredByTipoTramite(tipo.getId());
        if (required != null && !required.isEmpty()) {
            java.util.Set<Long> provided = new java.util.HashSet<>();
            if (req.getArchivos() != null) {
                for (var a : req.getArchivos()) {
                    if (a.getTipoDocumentoId() != null) {
                        provided.add(a.getTipoDocumentoId());
                    }
                }
            }

            java.util.List<TipoDocumento> missing = required.stream()
                    .filter(r -> r != null && !provided.contains(r.getId()))
                    .collect(java.util.stream.Collectors.toList());

            if (!missing.isEmpty()) {
                String names = missing.stream().map(t -> t.getNombre()).collect(java.util.stream.Collectors.joining(", "));
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Faltan documentos requeridos: " + names);
            }
        }

        var saved = repository.save(tramite);

        if (req.getArchivos() != null && !req.getArchivos().isEmpty()) {
            for (var a : req.getArchivos()) {
                ArchivoAdjunto adj = new ArchivoAdjunto();
                adj.setNombreArchivo(a.getNombreArchivo() != null ? a.getNombreArchivo() : a.getUrl());
                adj.setMime(a.getMime() != null ? a.getMime() : "application/octet-stream");
                adj.setTamano(a.getTamano() != null ? a.getTamano() : "0");
                adj.setTramite(saved);
                if (a.getTipoDocumentoId() != null) {
                    TipoDocumento td = new TipoDocumento();
                    td.setId(a.getTipoDocumentoId());
                    adj.setTipoDocumento(td);
                }
                archivoAdjuntoRepository.save(adj);
            }
        }

        return mapper.toDTO(saved);
    }
}
