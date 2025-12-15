package com.example.usco.tipoTramite.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.usco.estado.Estado;
import com.example.usco.tipoTramite.dtos.TipoTramiteDTO;
import com.example.usco.tipoTramite.mappers.TipoTramiteMapper;
import com.example.usco.tipoTramite.repositories.TipoTramiteRepository;

@Service
@RequiredArgsConstructor
public class TipoTramiteService {

    private final TipoTramiteRepository repository;
    private final TipoTramiteMapper mapper;

    public Page<TipoTramiteDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public Optional<TipoTramiteDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public TipoTramiteDTO create(TipoTramiteDTO dto) {
        dto.setId(null);
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public void update(Long id, TipoTramiteDTO dto) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("TipoTramite no encontrado"));

        dto.setId(id);
        repository.save(mapper.toEntity(dto));
    }

    public void delete(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("TipoTramite no encontrado"));

        repository.deleteById(id);
    }

    public void updateEstado(Long id, Long estadoId) {
        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("TipoTramite no encontrado"));

        Estado nuevoEstado = new Estado();
        nuevoEstado.setId(estadoId);
        entity.setEstado(nuevoEstado);

        repository.save(entity);
    }
}
