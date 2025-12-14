package com.example.usco.rol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usco.rol.Rol;

@Repository
public interface RolRepository
        extends JpaRepository<Rol, Long> {
}
