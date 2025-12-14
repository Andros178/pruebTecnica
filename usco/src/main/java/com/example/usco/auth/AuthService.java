package com.example.usco.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.usco.auth.dto.LoginRequest;
import com.example.usco.auth.dto.LoginResponse;
import com.example.usco.auth.dto.RegisterRequest;
import com.example.usco.persona.Persona;
import com.example.usco.rol.repositories.RolRepository;
import com.example.usco.usuarioRol.UsuarioRol;
import com.example.usco.usuarioRol.repositories.UsuarioRolRepository;
import com.example.usco.persona.repositories.PersonaRepository;
import com.example.usco.usuario.Usuario;
import com.example.usco.usuario.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final PersonaRepository personaRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final RolRepository rolRepository;

    @Transactional(readOnly = true)
    public LoginResponse authenticate(LoginRequest req) {
        Usuario user = usuarioRepository.findByNombre(req.getNombre())
            .orElseThrow(() -> new BadCredentialsException("Usuario no encontrado"));

        if (!passwordEncoder.matches(req.getContrasena(), user.getContrasena())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        // load active roles for user (estado id = 1)
        var roles = usuarioRolRepository.findAllByUsuario_IdAndEstado_Id(user.getId(), 1L)
            .stream()
            .map(ur -> ur.getRol().getNombre())
            .toList();

        String token = jwtUtil.generateToken(user.getNombre(), user.getId(), roles);
        return new LoginResponse(token, user.getNombre(), user.getId());
    }

    @Transactional
    public LoginResponse register(RegisterRequest req) {
        usuarioRepository.findByNombre(req.getNombre()).ifPresent(u -> {
            throw new RuntimeException("Usuario ya existe");
        });

        if (req.getPersonaId() == null) {
            throw new RuntimeException("personaId es requerido");
        }

        if (!personaRepository.existsById(req.getPersonaId())) {
            throw new RuntimeException("personaId no existe");
        }

        String encoded = passwordEncoder.encode(req.getContrasena());

        Usuario user = Usuario.builder()
                .nombre(req.getNombre())
                .contrasena(encoded)
                .build();

        Persona persona = new Persona();
        persona.setId(req.getPersonaId());
        user.setPersona(persona);

        Usuario saved = usuarioRepository.save(user);

        // assign default role ESTUDIANTE (id = 1) from DB
        var rol = rolRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("Rol ESTUDIANTE (id=1) no encontrado"));

        UsuarioRol usuarioRol = UsuarioRol.builder()
            .usuario(saved)
            .rol(rol)
            .build();

        usuarioRolRepository.save(usuarioRol);

        // load roles for token
        var roles = usuarioRolRepository.findAllByUsuario_IdAndEstado_Id(saved.getId(), 1L)
            .stream()
            .map(ur -> ur.getRol().getNombre())
            .toList();

        String token = jwtUtil.generateToken(saved.getNombre(), saved.getId(), roles);
        return new LoginResponse(token, saved.getNombre(), saved.getId());
    }

}
