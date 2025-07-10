package com.AgroTech.Usuarios.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.AgroTech.Usuarios.controller.usuarioController;
import com.AgroTech.Usuarios.model.Rol;
import com.AgroTech.Usuarios.model.Rol.NombreRol;
import com.AgroTech.Usuarios.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(usuarioController.class)
public class UsuarioServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObtenerUsuarios() throws Exception {
        Rol rolAdmin = Rol.builder()
            .id(1L)
            .nombre("ADMIN")
            .nombreRol(NombreRol.ADMIN)
            .build();

        Rol rolCliente = Rol.builder()
            .id(2L)
            .nombre("CLIENTE")
            .nombreRol(NombreRol.CLIENTE)
            .build();

        Usuario u1 = Usuario.builder()
            .id(1L)
            .username("jose123")
            .correo("jose@example.com")
            .password("1234")
            .rol(rolAdmin)
            .build();

        Usuario u2 = Usuario.builder()
            .id(2L)
            .username("maria456")
            .correo("maria@example.com")
            .password("5678")
            .rol(rolCliente)
            .build();

        when(usuarioService.findAll()).thenReturn(Arrays.asList(u1, u2));

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGuardarUsuario() throws Exception {
        Rol rolAdmin = Rol.builder()
            .id(1L)
            .nombre("ADMIN")
            .nombreRol(NombreRol.ADMIN)
            .build();

        Usuario nuevo = Usuario.builder()
            .username("ana789")
            .correo("ana@example.com")
            .password("clave123")
            .rol(rolAdmin)
            .build();

        Usuario guardado = Usuario.builder()
            .id(10L)
            .username("ana789")
            .correo("ana@example.com")
            .password("clave123")
            .rol(rolAdmin)
            .build();

        when(usuarioService.save(any(Usuario.class))).thenReturn(guardado);

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.correo").value("ana@example.com"));
    }
}