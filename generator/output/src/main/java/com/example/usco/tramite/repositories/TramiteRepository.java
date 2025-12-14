package com.example.usco.tramite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usco.tramite.Tramite;

@Repository
public interface TramiteRepository
        extends JpaRepository<Tramite, Long> {
}
