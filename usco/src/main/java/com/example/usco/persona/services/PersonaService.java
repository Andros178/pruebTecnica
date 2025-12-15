package com.example.usco.persona.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.usco.estado.Estado;
import com.example.usco.persona.dtos.PersonaDTO;
import com.example.usco.persona.mappers.PersonaMapper;
import com.example.usco.persona.repositories.PersonaRepository;
import com.example.usco.tipoIdentificacion.repositories.TipoIdentificacionRepository;
import com.example.usco.tipoIdentificacion.TipoIdentificacion;

@Service
@RequiredArgsConstructor
public class PersonaService {

    private final PersonaRepository repository;
    private final PersonaMapper mapper;
    private final TipoIdentificacionRepository tipoIdentificacionRepository;

    public Page<PersonaDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public Optional<PersonaDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public PersonaDTO create(PersonaDTO dto) {
        dto.setId(null);
        var entity = mapper.toEntity(dto);
        // Resolve tipoIdentificacion association if an id was provided
        try {
            if (dto.getTipoIdentificacionId() != null) {
                tipoIdentificacionRepository.findById(dto.getTipoIdentificacionId()).ifPresent(entity::setTipoIdentificacion);
            }
        } catch (Exception e) {
            // log and continue; validation will surface if needed
        }

        return mapper.toDTO(repository.save(entity));
    }

    public void update(Long id, PersonaDTO dto) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Persona no encontrado"));

        dto.setId(id);
        var entity = mapper.toEntity(dto);
        if (dto.getTipoIdentificacionId() != null) {
            tipoIdentificacionRepository.findById(dto.getTipoIdentificacionId()).ifPresent(entity::setTipoIdentificacion);
        }
        repository.save(entity);
    }

    public void delete(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Persona no encontrado"));

        repository.deleteById(id);
    }

    public void updateEstado(Long id, Long estadoId) {
        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Persona no encontrado"));

        Estado nuevoEstado = new Estado();
        nuevoEstado.setId(estadoId);
        entity.setEstado(nuevoEstado);

        repository.save(entity);
    }
}
