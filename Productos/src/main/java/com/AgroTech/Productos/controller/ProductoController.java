package com.AgroTech.Productos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AgroTech.Productos.model.Producto;
import com.AgroTech.Productos.service.ProductoSevice;

@RestController
@RequestMapping("/api/v1/productos")

public class ProductoController {

    @Autowired
    private ProductoSevice productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> lista = productoService.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/ProductoId")
    public ResponseEntity<Producto> getProductoById(Long ProductoId) {
        try {
            Producto producto = productoService.findById(ProductoId);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createProducto(@RequestBody Producto producto){
        try{
            Producto savedProducto = productoService.save(producto);
            return ResponseEntity.status(201).body(savedProducto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Error al crear el producto: " + e.getMessage());
        }
    }
}
