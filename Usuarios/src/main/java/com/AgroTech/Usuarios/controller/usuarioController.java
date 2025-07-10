package com.AgroTech.Usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AgroTech.Usuarios.model.Rol;
import com.AgroTech.Usuarios.model.Usuario;
import com.AgroTech.Usuarios.service.RolService;
import com.AgroTech.Usuarios.service.UsuarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1")
public class usuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RolService rolService;

    // GET /api/v1/Rol
    @GetMapping("/rol")
    public ResponseEntity<List<Rol>> getRol(){
        List<Rol> rols = rolService.obtenerRols();
        //verifico si la lista quedo vacia
        if(rols.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rols);
    }

    // GET /api/v1/usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuario(){
        List<Usuario> users = usuarioService.findAll();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    // PUT /api/v1/usuarios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(
        @PathVariable Long id,
        @RequestBody Usuario usuarioActualizado) {
    try {
        Usuario actualizado = usuarioService.update(id, usuarioActualizado);
        return ResponseEntity.ok(actualizado); // 200 OK
    } catch (RuntimeException e) {
        return ResponseEntity.status(404).body("Usuario no encontrado con ID: " + id);
        }
    }
    
    // POST /api/v1/usuarios
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
    try {
        String username = usuario.getUsername();
        String password = usuario.getPassword();
        Long rolId = usuario.getRol().getId();

        Usuario creado = usuarioService.crearUsuario(username, password, rolId);
        return ResponseEntity.status(201).body(creado); // 201 Created
    } catch (RuntimeException e) {
        return ResponseEntity.status(400).body("Error al crear usuario: " + e.getMessage());
    }
}

    // DELETE /api/v1/usuarios/{id}?solicitanteId=3
    @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteUsuario(
        @PathVariable Long id,
        @RequestParam Long solicitanteId) {
        try {
        //  Obtener usuario que realiza la solicitud
        Usuario solicitante = usuarioService.getById(solicitanteId);
        // Validar que tenga rol ADMIN
        if (!solicitante.getRol().getNombre().equalsIgnoreCase("ADMIN")) {
            return ResponseEntity.status(403).body("Acceso denegado: solo un ADMIN puede eliminar usuarios.");
        }
        //  Ejecutar la eliminación
        usuarioService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
            } catch (RuntimeException e) {
                return ResponseEntity.status(404).body("Error: " + e.getMessage());
            }
    }   
}
