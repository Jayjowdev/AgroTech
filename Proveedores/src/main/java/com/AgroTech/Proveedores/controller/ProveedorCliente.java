package com.AgroTech.Proveedores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AgroTech.Proveedores.model.Proveedor;
import com.AgroTech.Proveedores.service.ProveedorService;

@RestController
@RequestMapping("/api/v1/proveedores")

public class ProveedorCliente {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> getALLProveedor(){
        List<Proveedor> lista = proveedorService.findAll();
        if(lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/Id")
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable Long proveedorId) {
        try {
            Proveedor proveedor = proveedorService.findByProveedorId(proveedorId);
            return ResponseEntity.ok(proveedor);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{Id}")
    public ResponseEntity<Proveedor> createProveedor(Proveedor proveedor) {
        return ResponseEntity.status(201).body(proveedorService.save(proveedor));
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<?> deleteProveedor(@PathVariable Long proveedorId) {
        try {
            proveedorService.delete(proveedorId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
