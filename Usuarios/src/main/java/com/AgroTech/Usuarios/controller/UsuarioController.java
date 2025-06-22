package com.AgroTech.Usuarios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AgroTech.Usuarios.model.Usuario;
import com.AgroTech.Usuarios.service.UsuarioService;

@RestController
@RequestMapping("/api/vi/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(Usuario usuario) {
        if (usuarioService.existeUsuarioPorCorreo(usuario.getCorreo())) {
            return ResponseEntity.badRequest().build();
        }
        Usuario nuevoUsuario = usuarioService.creaUsuario(usuario);
        return ResponseEntity.status(201).body(nuevoUsuario);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
    try {
        Usuario actualizado = usuarioService.actualizarUsuario(id, usuarioActualizado);
        return ResponseEntity.ok(actualizado);
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
    try {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build(); // 204
    } catch (RuntimeException e) {
        return ResponseEntity.status(404).body("Usuario no encontrado: " + e.getMessage());
        }
    }
}
