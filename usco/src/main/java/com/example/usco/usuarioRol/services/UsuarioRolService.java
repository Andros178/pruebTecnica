package com.example.usco.usuarioRol.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.usco.usuarioRol.dtos.UsuarioRolDTO;
import com.example.usco.usuarioRol.mappers.UsuarioRolMapper;
import com.example.usco.usuarioRol.repositories.UsuarioRolRepository;
import com.example.usco.usuario.repositories.UsuarioRepository;
import com.example.usco.rol.repositories.RolRepository;
import com.example.usco.usuario.Usuario;
import com.example.usco.rol.Rol;
import com.example.usco.usuarioRol.repositories.UsuarioRolRepository;

@Service
@RequiredArgsConstructor
public class UsuarioRolService {

    private final UsuarioRolRepository repository;
    private final UsuarioRolMapper mapper;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public Page<UsuarioRolDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public Optional<UsuarioRolDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public UsuarioRolDTO create(UsuarioRolDTO dto) {
        dto.setId(null);
        var entity = mapper.toEntity(dto);

        if (dto.getUsuarioId() == null) {
            throw new RuntimeException("usuarioId es requerido");
        }
        if (dto.getRolId() == null) {
            throw new RuntimeException("rolId es requerido");
        }

        if (!usuarioRepository.existsById(dto.getUsuarioId())) {
            throw new RuntimeException("usuarioId no existe");
        }
        if (!rolRepository.existsById(dto.getRolId())) {
            throw new RuntimeException("rolId no existe");
        }

        // Prevent duplicate usuario+rol
        if (repository.existsByUsuario_IdAndRol_Id(dto.getUsuarioId(), dto.getRolId())) {
            throw new RuntimeException("La asociación usuario+rol ya existe");
        }

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());
        entity.setUsuario(usuario);

        Rol rol = new Rol();
        rol.setId(dto.getRolId());
        entity.setRol(rol);

        return mapper.toDTO(repository.save(entity));
    }

    public void update(Long id, UsuarioRolDTO dto) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("UsuarioRol no encontrado"));
        dto.setId(id);

        var entity = mapper.toEntity(dto);

        if (dto.getUsuarioId() != null) {
            if (!usuarioRepository.existsById(dto.getUsuarioId())) {
                throw new RuntimeException("usuarioId no existe");
            }
            Usuario usuario = new Usuario();
            usuario.setId(dto.getUsuarioId());
            entity.setUsuario(usuario);
        }

        if (dto.getRolId() != null) {
            if (!rolRepository.existsById(dto.getRolId())) {
                throw new RuntimeException("rolId no existe");
            }
            Rol rol = new Rol();
            rol.setId(dto.getRolId());
            entity.setRol(rol);
        }

        repository.save(entity);
    }

    public void delete(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new RuntimeException("UsuarioRol no encontrado"));

        repository.deleteById(id);
    }

    public void updateEstado(Long id, Long estadoId) {
        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("UsuarioRol no encontrado"));

        com.example.usco.estado.Estado nuevoEstado = new com.example.usco.estado.Estado();
        nuevoEstado.setId(estadoId);
        entity.setEstado(nuevoEstado);

        repository.save(entity);
    }
}
