package com.example.usco.usuario.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.example.usco.estado.Estado;
import com.example.usco.usuario.dtos.UsuarioDTO;
import com.example.usco.usuario.mappers.UsuarioMapper;
import com.example.usco.usuario.repositories.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    public Page<UsuarioDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public Optional<UsuarioDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @Transactional
    public UsuarioDTO create(UsuarioDTO dto) {
        dto.setId(null);
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public void update(Long id, UsuarioDTO dto) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        dto.setId(id);
        repository.save(mapper.toEntity(dto));
    }

    public void delete(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        repository.deleteById(id);
    }

    public void updateEstado(Long id, Long estadoId) {
        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Estado nuevoEstado = new Estado();
        nuevoEstado.setId(estadoId);
        entity.setEstado(nuevoEstado);

        repository.save(entity);
    }
}
