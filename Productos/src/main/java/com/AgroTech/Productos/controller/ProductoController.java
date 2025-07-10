package com.AgroTech.Productos.controller;

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

import com.AgroTech.Productos.model.Producto;
import com.AgroTech.Productos.service.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    
    // GET /api/v1/productos
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerProductos(){
        List<Producto> lista = productoService.findAll();
        if(lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    // GET /api/v1/productos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id){
        try{
            Producto nuevo = productoService.getProductoId(id);
            return ResponseEntity.ok(nuevo);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
     // POST /api/v1/productos
    @PostMapping
    public ResponseEntity<?> guardarProducto(@RequestBody Producto producto) {
        try {
            Producto guardado = productoService.saveProducto(producto);
            return ResponseEntity.status(201).body(guardado); // 201 creado
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Error al guardar producto: " + e.getMessage()); // 400 Bad Request
        }
    }

     // PUT /api/v1/productos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProducto(
            @PathVariable Long id,
            @RequestBody Producto producto) {
        try {
            Producto actualizado = productoService.updateProducto(id, producto);
            return ResponseEntity.ok(actualizado); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Producto no encontrado para actualizar con ID: " + id); // 404 Not Found
        }
    }
    // DELETE /api/v1/productos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        try {
            productoService.deleteProducto(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Producto no encontrado para eliminar con ID: " + id); // 404 Not Found
        }
    }
}
