package com.example.usco.tipoIdentificacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usco.tipoIdentificacion.TipoIdentificacion;

@Repository
public interface TipoIdentificacionRepository
        extends JpaRepository<TipoIdentificacion, Long> {
}
