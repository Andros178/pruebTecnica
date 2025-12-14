package com.example.usco.archivoAdjunto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usco.archivoAdjunto.ArchivoAdjunto;

@Repository
public interface ArchivoAdjuntoRepository
        extends JpaRepository<ArchivoAdjunto, Long> {
}
