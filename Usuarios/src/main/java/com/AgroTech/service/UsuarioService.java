package com.AgroTech.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AgroTech.model.Historial;
import com.AgroTech.model.Rol;
import com.AgroTech.model.Usuario;
import com.AgroTech.repository.RolRepository;
import com.AgroTech.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(String username, String password, String nombreRol) {
        if (usuarioRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("El usuario ya existe");
        }
        Rol rol = rolRepository.findByNombreRol("Rol" + nombreRol);
        if (rol == null) {
            rol = new Rol();
            rolRepository.save(rol);
        }
            Usuario usuario = new Usuario();
            usuario.setUsername(username);
            usuario.setPassword(passwordEncoder.encode(password));
            usuario.setRol(Set.of(rol));

            Historial historial = new Historial();
            historial.setUsuario(usuario);
            usuario.setHistorial(List.of(historial));
       
        return usuarioRepository.save(usuario);
    }

    public List <Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public void eliminarUsuario(Long usuarioId){
        usuarioRepository.deleteById(usuarioId);
    }

    public boolean existeUsuarioPorCorreo(String correo) {
    return usuarioRepository.existsByCorreo(correo);
}

    public Usuario obtenerUsuarioPorId(Long usuarioId){
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + usuarioId));
    }

    public void actualizarLogin(Long usuarioId){
        Usuario usuario = obtenerUsuarioPorId(usuarioId);
        if (usuario != null && usuario.getHistorial() != null && !usuario.getHistorial().isEmpty()){
            // Set ultimoLogin en la primera entrada de historial
            usuario.getHistorial().get(0).setUltimoLogin(new Date());
            usuarioRepository.save(usuario);
        }
    }
}
