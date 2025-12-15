package com.example.usco.archivoAdjunto.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.usco.archivoAdjunto.dtos.ArchivoAdjuntoDTO;
import com.example.usco.archivoAdjunto.mappers.ArchivoAdjuntoMapper;
import com.example.usco.archivoAdjunto.repositories.ArchivoAdjuntoRepository;

@Service
@RequiredArgsConstructor
public class ArchivoAdjuntoService {

    private final ArchivoAdjuntoRepository repository;
    private final ArchivoAdjuntoMapper mapper;

    public Page<ArchivoAdjuntoDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public Optional<ArchivoAdjuntoDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public ArchivoAdjuntoDTO create(ArchivoAdjuntoDTO dto) {
        dto.setId(null);
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public void update(Long id, ArchivoAdjuntoDTO dto) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("ArchivoAdjunto no encontrado"));

        dto.setId(id);
        repository.save(mapper.toEntity(dto));
    }

    public void delete(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("ArchivoAdjunto no encontrado"));

        repository.deleteById(id);
    }

    public void updateEstado(Long id, Long estadoId) {
        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("ArchivoAdjunto no encontrado"));

        Estado nuevoEstado = new Estado();
        nuevoEstado.setId(estadoId);
        entity.setEstado(nuevoEstado);

        repository.save(entity);
    }
}
