package com.AgroTech.Incidencias.controller;

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

import com.AgroTech.Incidencias.model.Incidencia;
import com.AgroTech.Incidencias.service.IncidenciaService;

@RestController
@RequestMapping("/api/v1/incidencia")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    // GET /api/v1/incidencias
    @GetMapping
    public ResponseEntity<List<Incidencia>>getAll(){
        return ResponseEntity.ok(incidenciaService.findAll());
    }
    // GET /api/v1/incidencias/{id}
     @GetMapping("/{id}")
    public ResponseEntity<Incidencia> getById(@PathVariable Long id) {
        return ResponseEntity.ok(incidenciaService.getById(id));
    }

    // POST /api/v1/incidencias/{id}
    @PostMapping("/{id}")
    public ResponseEntity<?> create(@RequestBody Incidencia incidencia) {
        try {
            Incidencia nueva = incidenciaService.saveIncidencia(incidencia);
            return ResponseEntity.status(201).body(nueva); // 201 Created
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Error al crear incidencia: " + e.getMessage()); // 400 Bad Request
        }
    }

    // PUT /api/v1/incidencias/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody Incidencia incidencia) {
        try {
            Incidencia actualizada = incidenciaService.updateIncidencia(id, incidencia);
            return ResponseEntity.ok(actualizada); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Incidencia no encontrada para actualizar: " + e.getMessage()); //404 not found
        }
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncidencia(@PathVariable Long id) {
        try {
            incidenciaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Incidencia no encontrada con ID: " + id); 
        }
    }
}