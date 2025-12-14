package com.example.usco.tipoTramite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usco.tipoTramite.TipoTramite;

@Repository
public interface TipoTramiteRepository
        extends JpaRepository<TipoTramite, Long> {
}
