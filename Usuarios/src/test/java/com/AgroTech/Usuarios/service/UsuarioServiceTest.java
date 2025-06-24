package com.AgroTech.Usuarios.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.AgroTech.model.Usuario;
import com.AgroTech.repository.RolRepository;
import com.AgroTech.repository.UsuarioRepository;
import com.AgroTech.service.UsuarioService;

class UsuarioServiceTest {

    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private PasswordEncoder passwordEncoder;
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        rolRepository = mock(RolRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        usuarioService = new UsuarioService(usuarioRepository, rolRepository, passwordEncoder);
    }

    @Test
    void testExisteUsuarioPorCorreo() {
        String correo = "test@correo.com";
        when(usuarioRepository.existsByCorreo(correo)).thenReturn(true);

        assertTrue(usuarioService.existeUsuarioPorCorreo(correo));
        verify(usuarioRepository, times(1)).existsByCorreo(correo);
    }

    @Test
    void testCreaUsuario() {
        Usuario usuario = new Usuario();
        usuario.setCorreo("nuevo@correo.com");
        usuario.setPassword("1234");

        when(usuarioRepository.existsByCorreo(usuario.getCorreo())).thenReturn(false);
        when(passwordEncoder.encode(usuario.getPassword())).thenReturn("encrypted");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        String username = "nuevo@correo.com";
        String password = "1234";
        String nombreRol = "USER";
        Usuario creado = usuarioService.registrarUsuario(username, password, nombreRol);

        assertNotNull(creado);
        assertEquals("nuevo@correo.com", creado.getCorreo());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testCreaUsuarioYaExistente() {
        Usuario usuario = new Usuario();
        usuario.setCorreo("existente@correo.com");

        when(usuarioRepository.existsByCorreo(usuario.getCorreo())).thenReturn(true);

        String username = "existente@correo.com";
        String password = "1234";
        String nombreRol = "USER";
        Usuario creado = usuarioService.registrarUsuario(username, password, nombreRol);

        assertNull(creado);
        verify(usuarioRepository, never()).save(any());
    }
}