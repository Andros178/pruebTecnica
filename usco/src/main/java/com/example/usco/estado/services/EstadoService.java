package com.example.usco.estado.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.usco.estado.dtos.EstadoDTO;
import com.example.usco.estado.mappers.EstadoMapper;
import com.example.usco.estado.repositories.EstadoRepository;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRepository repository;
    private final EstadoMapper mapper;

    public Page<EstadoDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public Optional<EstadoDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public EstadoDTO create(EstadoDTO dto) {
        dto.setId(null);
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public void update(Long id, EstadoDTO dto) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        dto.setId(id);
        repository.save(mapper.toEntity(dto));
    }

    public void delete(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        repository.deleteById(id);
    }

}
