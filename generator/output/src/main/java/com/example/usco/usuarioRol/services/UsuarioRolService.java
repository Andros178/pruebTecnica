package com.example.usco.usuarioRol.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.usco.usuarioRol.dtos.UsuarioRolDTO;
import com.example.usco.usuarioRol.mappers.UsuarioRolMapper;
import com.example.usco.usuarioRol.repositories.UsuarioRolRepository;

@Service
@RequiredArgsConstructor
public class UsuarioRolService {

    private final UsuarioRolRepository repository;
    private final UsuarioRolMapper mapper;

    public Page<UsuarioRolDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public Optional<UsuarioRolDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public UsuarioRolDTO create(UsuarioRolDTO dto) {
        dto.setId(null);
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public void update(Long id, UsuarioRolDTO dto) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("UsuarioRol no encontrado"));

        dto.setId(id);
        repository.save(mapper.toEntity(dto));
    }

    public void delete(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("UsuarioRol no encontrado"));

        repository.deleteById(id);
    }

    public void updateEstado(Long id, Long estadoId) {
        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("UsuarioRol no encontrado"));

        com.example.usco.estado.Estado nuevoEstado = new com.example.usco.estado.Estado();
        nuevoEstado.setId(estadoId);
        entity.setEstado(nuevoEstado);

        repository.save(entity);
    }
}
