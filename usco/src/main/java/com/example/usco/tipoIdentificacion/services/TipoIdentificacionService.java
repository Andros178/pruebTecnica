package com.example.usco.tipoIdentificacion.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.usco.estado.Estado;
import com.example.usco.tipoIdentificacion.dtos.TipoIdentificacionDTO;
import com.example.usco.tipoIdentificacion.mappers.TipoIdentificacionMapper;
import com.example.usco.tipoIdentificacion.repositories.TipoIdentificacionRepository;

@Service
@RequiredArgsConstructor
public class TipoIdentificacionService {

    private final TipoIdentificacionRepository repository;
    private final TipoIdentificacionMapper mapper;

    public Page<TipoIdentificacionDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public Optional<TipoIdentificacionDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public TipoIdentificacionDTO create(TipoIdentificacionDTO dto) {
        dto.setId(null);
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public void update(Long id, TipoIdentificacionDTO dto) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("TipoIdentificacion no encontrado"));

        dto.setId(id);
        repository.save(mapper.toEntity(dto));
    }

    public void delete(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("TipoIdentificacion no encontrado"));

        repository.deleteById(id);
    }

    public void updateEstado(Long id, Long estadoId) {
        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("TipoIdentificacion no encontrado"));

        Estado nuevoEstado = new Estado();
        nuevoEstado.setId(estadoId);
        entity.setEstado(nuevoEstado);

        repository.save(entity);
    }
}
