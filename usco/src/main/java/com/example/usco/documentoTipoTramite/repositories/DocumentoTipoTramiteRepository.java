package com.example.usco.documentoTipoTramite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usco.documentoTipoTramite.DocumentoTipoTramite;

@Repository
public interface DocumentoTipoTramiteRepository extends JpaRepository<DocumentoTipoTramite, Long> {
    List<DocumentoTipoTramite> findAllByTipoTramite_IdAndEstado_Id(Long tipoTramiteId, Long estadoId);
}
