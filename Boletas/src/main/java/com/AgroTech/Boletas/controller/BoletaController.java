package com.AgroTech.Boletas.controller;

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

import com.AgroTech.Boletas.model.Boleta;
import com.AgroTech.Boletas.service.BoletaService;

@RestController
@RequestMapping("/api/v1/boletas")
public class BoletaController {

    @Autowired
    private BoletaService boletaService;

    // GET /api/v1/boletas
    @GetMapping
    public ResponseEntity<List<Boleta>> getAll(){
        return ResponseEntity.ok(boletaService.findAll());
    }
    // GET /api/v1/boletas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Boleta> getById(@PathVariable Long id){
        return ResponseEntity.ok(boletaService.getById(id));
    }

    // POST /api/v1/boletas
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Boleta boleta){
        try {
            Boleta nuevaBoleta = boletaService.save(boleta);
            return ResponseEntity.status(201).body(nuevaBoleta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Error al crear boleta: " + e.getMessage());
        }
    }

    // PUT /api/v1/boletas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Boleta boleta){
        try {
            Boleta actualizada = boletaService.updateBoleta(id, boleta);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Boleta no encontrada para actualizar: " + e.getMessage());
        }
    }
    
    // DELETE /api/v1/boletas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long Id) {
        try {
            boletaService.delete(Id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Boleta no encontrada para eliminar.");
        }
    }
}
