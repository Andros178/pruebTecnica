package com.example.usco.persona.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.usco.persona.dtos.PersonaDTO;
import com.example.usco.persona.mappers.PersonaMapper;
import com.example.usco.persona.repositories.PersonaRepository;

@Service
@RequiredArgsConstructor
public class PersonaService {

    private final PersonaRepository repository;
    private final PersonaMapper mapper;

    public Page<PersonaDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public Optional<PersonaDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public PersonaDTO create(PersonaDTO dto) {
        dto.setId(null);
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    public void update(Long id, PersonaDTO dto) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Persona no encontrado"));

        dto.setId(id);
        repository.save(mapper.toEntity(dto));
    }

    public void delete(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Persona no encontrado"));

        repository.deleteById(id);
    }

    public void updateEstado(Long id, Long estadoId) {
        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Persona no encontrado"));

        com.example.usco.estado.Estado nuevoEstado = new com.example.usco.estado.Estado();
        nuevoEstado.setId(estadoId);
        entity.setEstado(nuevoEstado);

        repository.save(entity);
    }
}
