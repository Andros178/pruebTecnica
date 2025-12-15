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
import com.example.usco.rol.mappers.RolMapper;
import com.example.usco.rol.dtos.RolDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
import com.example.usco.usuario.Usuario;
import com.example.usco.estado.Estado;
import com.example.usco.estado.repositories.EstadoRepository;
import com.example.usco.rol.Rol;
import com.example.usco.usuarioRol.repositories.UsuarioRolRepository;

@Service
@RequiredArgsConstructor
public class UsuarioRolService {

    private final UsuarioRolRepository repository;
    private final UsuarioRolMapper mapper;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final RolMapper rolMapper;
    private final EstadoRepository estadoRepository;

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

        
        if (repository.existsByUsuario_IdAndRol_Id(dto.getUsuarioId(), dto.getRolId())) {
            throw new RuntimeException("La asociación usuario+rol ya existe");
        }

        Usuario usuarioRef = usuarioRepository.getReferenceById(dto.getUsuarioId());
        entity.setUsuario(usuarioRef);

        Rol rolRef = rolRepository.getReferenceById(dto.getRolId());
        entity.setRol(rolRef);

        
        if (dto.getEstadoId() != null) {
            if (!estadoRepository.existsById(dto.getEstadoId())) {
                throw new RuntimeException("estadoId no existe");
            }
            Estado estadoRef = estadoRepository.getReferenceById(dto.getEstadoId());
            entity.setEstado(estadoRef);
        } else {
        
            Estado estadoRef = estadoRepository.getReferenceById(1L);
            entity.setEstado(estadoRef);
        }

        return mapper.toDTO(repository.save(entity));
    }

    public void update(Long id, UsuarioRolDTO dto) {
        var entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("UsuarioRol no encontrado"));

        
        if (dto.getUsuarioId() != null) {
            if (!usuarioRepository.existsById(dto.getUsuarioId())) {
                throw new RuntimeException("usuarioId no existe");
            }
            Usuario usuarioRef = usuarioRepository.getReferenceById(dto.getUsuarioId());
            entity.setUsuario(usuarioRef);
        }

        if (dto.getRolId() != null) {
            if (!rolRepository.existsById(dto.getRolId())) {
                throw new RuntimeException("rolId no existe");
            }
            Rol rolRef = rolRepository.getReferenceById(dto.getRolId());
            entity.setRol(rolRef);
        }

        if (dto.getEstadoId() != null) {
            if (!estadoRepository.existsById(dto.getEstadoId())) {
                throw new RuntimeException("estadoId no existe");
            }
            Estado estadoRef = estadoRepository.getReferenceById(dto.getEstadoId());
            entity.setEstado(estadoRef);
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

        if (!estadoRepository.existsById(estadoId)) {
            throw new RuntimeException("estadoId no existe");
        }
        Estado estadoRef = estadoRepository.getReferenceById(estadoId);
        entity.setEstado(estadoRef);

        repository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<RolDTO> findRolesByUsuario(Long usuarioId) {
        var list = repository.findAllByUsuario_IdAndEstado_Id(usuarioId, 1L);
        return list.stream()
            .map(ur -> rolMapper.toDTO(ur.getRol()))
            .collect(Collectors.toList());
    }
}
