package com.example.usco.usuarioRol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usco.usuarioRol.UsuarioRol;

@Repository
public interface UsuarioRolRepository
        extends JpaRepository<UsuarioRol, Long> {

        boolean existsByUsuario_IdAndRol_Id(Long usuarioId, Long rolId);

        java.util.List<UsuarioRol> findAllByUsuario_IdAndEstado_Id(Long usuarioId, Long estadoId);
        
        java.util.List<UsuarioRol> findAllByRol_NombreAndEstado_Id(String rolNombre, Long estadoId);
}
