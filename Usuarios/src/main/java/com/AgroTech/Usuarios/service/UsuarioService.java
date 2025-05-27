package com.AgroTech.Usuarios.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AgroTech.Usuarios.model.Usuario;
import com.AgroTech.Usuarios.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existeUsuarioPorCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    public Usuario creaUsuario(Usuario usuario) {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está en uso");
        }

        if (usuario.getRol() == Usuario.Rol.ADMIN) {
            throw new RuntimeException("No se puede crear un usuario con rol ADMIN directamente");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario actualizarUsuario(Long id, Usuario datos) {
        Usuario existente = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        
            existente.setNombre(datos.getNombre());
            existente.setCorreo(datos.getCorreo());
        
        if (datos.getPassword() != null && !datos.getPassword().isBlank()) {
            existente.setPassword(passwordEncoder.encode(datos.getPassword()));
        }
        existente.setRol(datos.getRol());
        return usuarioRepository.save(existente);
    }

    public void CambiarPassword(Long id, String nuevaPassword){
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        
        if (nuevaPassword == null || nuevaPassword.isBlank()) {
            throw new RuntimeException("La nueva contraseña no puede estar vacía");
        }
        
        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}
