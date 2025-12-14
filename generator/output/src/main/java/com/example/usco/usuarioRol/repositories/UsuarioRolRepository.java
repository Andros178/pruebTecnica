package com.example.usco.usuarioRol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usco.usuarioRol.UsuarioRol;

@Repository
public interface UsuarioRolRepository
        extends JpaRepository<UsuarioRol, Long> {
}
