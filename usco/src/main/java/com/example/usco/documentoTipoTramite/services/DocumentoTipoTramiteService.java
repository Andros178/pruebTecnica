package com.example.usco.documentoTipoTramite.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.example.usco.documentoTipoTramite.DocumentoTipoTramite;
import com.example.usco.documentoTipoTramite.repositories.DocumentoTipoTramiteRepository;
import com.example.usco.tipoDocumento.TipoDocumento;
import com.example.usco.tipoDocumento.dtos.TipoDocumentoDTO;
import com.example.usco.tipoDocumento.mappers.TipoDocumentoMapper;

@Service
@RequiredArgsConstructor
public class DocumentoTipoTramiteService {

    private final DocumentoTipoTramiteRepository repository;
    private final TipoDocumentoMapper mapper;

    @Transactional(readOnly = true)
    public List<TipoDocumentoDTO> findRequiredByTipoTramite(Long tipoTramiteId) {
        
        var list = repository.findAllByTipoTramite_IdAndEstado_Id(tipoTramiteId, 1L);
        
        return list.stream()
                .map(DocumentoTipoTramite::getTipoDocumento)
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
