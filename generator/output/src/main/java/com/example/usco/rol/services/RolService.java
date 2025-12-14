package com.example.usco.rol.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.usco.rol.dtos.RolDTO;
import com.example.usco.rol.mappers.RolMapper;
import com.example.usco.rol.repositories.RolRepository;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository repository;
    private final RolMapper mapper;

    public Page<RolDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public Optional<RolDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public RolDTO create(RolDTO dto) {
        dto.setId(null);
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public void update(Long id, RolDTO dto) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        dto.setId(id);
        repository.save(mapper.toEntity(dto));
    }

    public void delete(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        repository.deleteById(id);
    }

    public void updateEstado(Long id, Long estadoId) {
        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        com.example.usco.estado.Estado nuevoEstado = new com.example.usco.estado.Estado();
        nuevoEstado.setId(estadoId);
        entity.setEstado(nuevoEstado);

        repository.save(entity);
    }
}
