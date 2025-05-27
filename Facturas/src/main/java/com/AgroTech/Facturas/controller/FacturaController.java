package com.AgroTech.Facturas.controller;

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

import com.AgroTech.Facturas.model.Factura;
import com.AgroTech.Facturas.service.FacturaService;

@RestController
@RequestMapping("/api/v1/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;


    @GetMapping
    public ResponseEntity<List<Factura>> getAllFacturas() {
        List<Factura> lista2 = facturaService.findAll();
        if (lista2.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista2);
    }

    @GetMapping("/Id")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Long FacturaId){
        try {
            Factura factura = facturaService.findByFacturaId(FacturaId);
            return ResponseEntity.ok(factura);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{Id}")
    public ResponseEntity<?> createFactura(@RequestBody Factura factura){
        try {
            Factura savedFactura = facturaService.save(factura);
            return ResponseEntity.status(201).body(savedFactura);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Error al crear la factura: " + e.getMessage());
        }
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<?> deleteFactura(@PathVariable Long FacturaId) {
        try {
            facturaService.delete(FacturaId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
