package com.example.usco.tramite.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.usco.tramite.dtos.TramiteDTO;
import com.example.usco.tramite.mappers.TramiteMapper;
import com.example.usco.tramite.repositories.TramiteRepository;

@Service
@RequiredArgsConstructor
public class TramiteService {

    private final TramiteRepository repository;
    private final TramiteMapper mapper;

    public Page<TramiteDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
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

        com.example.usco.estado.Estado nuevoEstado = new com.example.usco.estado.Estado();
        nuevoEstado.setId(estadoId);
        entity.setEstado(nuevoEstado);

        repository.save(entity);
    }
}
