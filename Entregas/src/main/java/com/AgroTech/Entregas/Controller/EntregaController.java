package com.AgroTech.Entregas.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AgroTech.Entregas.model.Entrega;
import com.AgroTech.Entregas.service.EntregaService;


@RestController
@RequestMapping("/api/v1/entregas")
public class EntregaController {
    
    @Autowired
    private EntregaService entregaService;

    // GET /api/v1/entregas
    @GetMapping
    public ResponseEntity<List<Entrega>> obtenerEntregas(){
        return ResponseEntity.ok(entregaService.obtenerEntregas());
    }

    // GET /api/v1/entregas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Entrega> getEngregaId(@PathVariable Long id){
        return ResponseEntity.ok(entregaService.getEntregaId(id));
    }

    // POST /api/v1/entregas?estado=EN_TRANSITO&fechaEstimada=2025-07-15
    @PostMapping
    public ResponseEntity<?> crearEntrega(
            @RequestParam String estado,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaEstimada) {
        try {
            Entrega nueva = entregaService.crear(estado, fechaEstimada);
            return ResponseEntity.status(201).body(nueva); // 201 Created
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Error al crear entrega: " + e.getMessage()); // 400 Bad Request
        }
    }

    // PUT /api/v1/entregas/{id}?estado=ENTREGADO&fechaEstimada=2025-07-15
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEntrega(
            @PathVariable Long id,
            @RequestParam String estado,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaEstimada) {
        try {
            Entrega actualizada = entregaService.actualizarEntrega(id, estado, fechaEstimada);
            return ResponseEntity.ok(actualizada); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Entrega no encontrada: " + e.getMessage());
        }
    }

    // DELETE /api/v1/entregas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntrega(@PathVariable Long id) {
        try {
            entregaService.delete(id);
            return ResponseEntity.noContent().build(); // 204 No Content si se elimina correctamente
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Entrega no encontrada con ID: " + id); // 404 Not Found
        }
    }


}
