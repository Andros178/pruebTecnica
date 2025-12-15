package com.example.usco.rol.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.example.usco.estado.Estado;
import com.example.usco.estado.repositories.EstadoRepository;
import com.example.usco.rol.dtos.RolDTO;
import com.example.usco.rol.mappers.RolMapper;
import com.example.usco.rol.repositories.RolRepository;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository repository;
    private final RolMapper mapper;
    private final EstadoRepository estadoRepository;

    public Page<RolDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public Optional<RolDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @Transactional
    public RolDTO create(RolDTO dto) {
        dto.setId(null);
        var entity = mapper.toEntity(dto);

        
        if (dto.getEstadoId() != null) {
            if (!estadoRepository.existsById(dto.getEstadoId())) {
                throw new RuntimeException("estadoId no existe");
            }
            Estado estadoRef = estadoRepository.getReferenceById(dto.getEstadoId());
            entity.setEstado(estadoRef);
        }

        return mapper.toDTO(repository.save(entity));
    }

    public void update(Long id, RolDTO dto) {
        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        
        if (dto.getNombre() != null) {
            entity.setNombre(dto.getNombre());
        }

        if (dto.getEstadoId() != null) {
            if (!estadoRepository.existsById(dto.getEstadoId())) {
                throw new RuntimeException("estadoId no existe");
            }
            Estado estadoRef = estadoRepository.getReferenceById(dto.getEstadoId());
            entity.setEstado(estadoRef);
        }

        repository.save(entity);
    }

    public void delete(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        repository.deleteById(id);
    }

    public void updateEstado(Long id, Long estadoId) {
        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        if (!estadoRepository.existsById(estadoId)) {
            throw new RuntimeException("estadoId no existe");
        }
        Estado estadoRef = estadoRepository.getReferenceById(estadoId);
        entity.setEstado(estadoRef);

        repository.save(entity);
    }
}
