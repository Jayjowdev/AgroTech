package com.AgroTech.Entregas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AgroTech.Entregas.Model.Entrega;
import com.AgroTech.Entregas.Service.EntregaService;

@RestController
@RequestMapping("/api/v1/entregas")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @GetMapping
    public ResponseEntity <List<Entrega>> getAllEntregas() {
        List<Entrega> lista = entregaService.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/id")
    public ResponseEntity <Entrega> getEntregaById(@PathVariable  Long entregaId) {
        try {
            Entrega entrega = entregaService.findByEntregaId(entregaId);
            return ResponseEntity.ok(entrega);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity <?> createEntrega(@RequestBody Entrega entrega) {
        try {
            Entrega savedEntrega = entregaService.save(entrega);
        return ResponseEntity.status(201).body(savedEntrega);
    } catch (RuntimeException e) {
        return ResponseEntity.status(404).body("Error al crear la entrega: " + e.getMessage());
    }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <?> deleteEntrega(@PathVariable Long entregaId) {
        try {
            entregaService.delete(entregaId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
