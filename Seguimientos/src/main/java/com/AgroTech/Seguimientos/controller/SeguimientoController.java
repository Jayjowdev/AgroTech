package com.AgroTech.Seguimientos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AgroTech.Seguimientos.model.Seguimiento;
import com.AgroTech.Seguimientos.service.SeguimientoService;

@RestController
@RequestMapping("/api/v1/seguimientos")
public class SeguimientoController {

    @Autowired
    private SeguimientoService seguimientoService;

    // GET /api/v1/seguimientos
    @GetMapping
    public ResponseEntity<List<Seguimiento>> getAll(){
        return ResponseEntity.ok(seguimientoService.findAll());
    }

    // GET /api/v1/seguimientos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Seguimiento seguimiento = seguimientoService.getById(id);
            return ResponseEntity.ok(seguimiento); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Seguimiento no encontrado con ID: " + id); // 404 Not Found
        }
    }

    // POST /api/v1/seguimientos
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Seguimiento seguimiento) {
        try {
            Seguimiento nuevo = seguimientoService.SaveSeguimiento(seguimiento);
            return ResponseEntity.status(201).body(nuevo); // 201 Created
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Error al crear seguimiento: " + e.getMessage()); // 400 Bad Request
        }
    }

    // PUT /api/v1/seguimientos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Seguimiento seguimiento) {
        try {
            Seguimiento actualizado = seguimientoService.UpdateSeguimiento(id, seguimiento);
            return ResponseEntity.ok(actualizado); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Seguimiento no encontrado para actualizar con ID: " + id); // 404 Not Found
        }
    }
    // DELETE /api/v1/seguimientos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            seguimientoService.deleteSeguimiento(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Seguimiento no encontrado para eliminar con ID: " + id); // 404 Not Found
        }
    }


}
