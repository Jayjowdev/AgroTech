package com.AgroTech.Usuarios.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.AgroTech.controller.UsuarioController;
import com.AgroTech.model.Usuario;
import com.AgroTech.service.UsuarioService;

class UsuarioControllerTest {

    private UsuarioService usuarioService;
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        usuarioService = mock(UsuarioService.class);
        usuarioController = new UsuarioController(usuarioService);
    }

    @Test
    void testRegistrarUsuarioExitoso() {
        Usuario usuario = new Usuario();
        usuario.setCorreo("nuevo@correo.com");

        when(usuarioService.registrarUsuario(usuario.getCorreo(), usuario.getNombre(), usuario.getPassword())).thenReturn(usuario);

        Usuario respuesta = usuarioController.registrarUsuario(
            usuario.getCorreo(),
            usuario.getNombre(),
            usuario.getPassword()
        );

        assertEquals(usuario, respuesta);
    }

    @Test
    void testRegistrarUsuarioFallido() {
        Usuario usuario = new Usuario();
        usuario.setCorreo("existente@correo.com");

        when(usuarioService.registrarUsuario(usuario.getCorreo(), usuario.getNombre(), usuario.getPassword())).thenReturn(null);

        Usuario respuesta = usuarioController.registrarUsuario(
            usuario.getCorreo(),
            usuario.getNombre(),
            usuario.getPassword()
        );

        assertNull(respuesta, "El usuario debe ser null cuando el registro falla");
    }
}