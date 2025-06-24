package com.AgroTech.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.stream.Collectors;

import com.AgroTech.model.Usuario;
import com.AgroTech.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
    
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
            usuario.getUsername(),
            usuario.getPassword(),
            usuario.getRol().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombreRol()))
                .collect(Collectors.toList())
        );
    }
}
