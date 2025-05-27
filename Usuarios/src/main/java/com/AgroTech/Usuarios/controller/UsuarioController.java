package com.AgroTech.Usuarios.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AgroTech.Usuarios.dto.CambioPasswordRequest;
import com.AgroTech.Usuarios.dto.UsuarioResponse;
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

   @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
    List<Usuario> usuarios = usuarioService.obtenerTodos();
    List<UsuarioResponse> respuesta = usuarios.stream()
        .map(u -> new UsuarioResponse(u.getUsuarioId(), u.getNombre(), u.getCorreo(), u.getRol()))
        .toList();
    return ResponseEntity.ok(respuesta);
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

    @PutMapping("/{id}/password")
    public ResponseEntity<?> cambiarPassword(@PathVariable Long id, @RequestBody CambioPasswordRequest request) {
    try {
        usuarioService.CambiarPassword(id, request.getNuevaPassword());
        return ResponseEntity.ok("Contraseña actualizada correctamente");
    } catch (RuntimeException e) {
        return ResponseEntity.status(404).body("Error: " + e.getMessage());
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
