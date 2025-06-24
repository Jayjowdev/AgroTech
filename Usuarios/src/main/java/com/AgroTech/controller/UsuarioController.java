package com.AgroTech.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AgroTech.model.Usuario;
import com.AgroTech.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor

public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/registrar")
    public  Usuario registrarUsuario(
        @RequestParam String username,
        @RequestParam String password,
        @RequestParam(defaultValue = "USER") String rol) {
            return usuarioService.registrarUsuario(username, password, rol);
        }
    
        @GetMapping("/{id}")
        public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
            return usuarioService.obtenerUsuarioPorId(id);
        }

        @GetMapping("/{historialId}/update-login")
        public void actualizarLogin(@PathVariable Long historialId) {
            usuarioService.actualizarLogin(historialId);
        }

        @DeleteMapping("/{id}")
        @PreAuthorize("hasRole('ADMIN')")
        public void eliminarUsuario(@PathVariable Long id) {
            usuarioService.eliminarUsuario(id);
        }

}
