package com.example.usco.documentoTipoTramite.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.example.usco.documentoTipoTramite.DocumentoTipoTramite;
import com.example.usco.documentoTipoTramite.repositories.DocumentoTipoTramiteRepository;
import com.example.usco.tipoDocumento.TipoDocumento;

@Service
@RequiredArgsConstructor
public class DocumentoTipoTramiteService {

    private final DocumentoTipoTramiteRepository repository;

    @Transactional(readOnly = true)
    public List<TipoDocumento> findRequiredByTipoTramite(Long tipoTramiteId) {
        // estado 1 = ACTIVO by convention
        var list = repository.findAllByTipoTramite_IdAndEstado_Id(tipoTramiteId, 1L);
        return list.stream()
                .map(DocumentoTipoTramite::getTipoDocumento)
                .collect(Collectors.toList());
    }
}
