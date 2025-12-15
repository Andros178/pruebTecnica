package com.example.usco.seguimiento.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

import com.example.usco.seguimiento.Seguimiento;
import com.example.usco.seguimiento.dtos.SeguimientoDTO;
import com.example.usco.seguimiento.mappers.SeguimientoMapper;
import com.example.usco.seguimiento.repositories.SeguimientoRepository;
import com.example.usco.tramite.repositories.TramiteRepository;

@Service
@RequiredArgsConstructor
public class SeguimientoService {

    private final SeguimientoRepository seguimientoRepository;
    private final SeguimientoMapper seguimientoMapper;
    private final TramiteRepository tramiteRepository;

    @Transactional(readOnly = true)
    public List<SeguimientoDTO> timeline(Long tramiteId) {
        if (!tramiteRepository.existsById(tramiteId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tramite no encontrado");
        }

        return seguimientoRepository.findAllByTramite_IdOrderByCreatedAtAsc(tramiteId)
            .stream()
            .map(seguimientoMapper::toDTO)
            .collect(Collectors.toList());
    }
}
