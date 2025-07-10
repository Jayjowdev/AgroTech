package com.AgroTech.Pedidos.controller;

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

import com.AgroTech.Pedidos.model.Pedido;
import com.AgroTech.Pedidos.service.PedidoService;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {
@Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAll() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    // GET /api/v1/pedidos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Pedido pedido = pedidoService.getById(id);
            return ResponseEntity.ok(pedido); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Pedido no encontrado con ID: " + id); // 404 Not Found
        }
    }

    // POST /api/v1/pedidos
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Pedido pedido) {
        try {
            Pedido nuevo = pedidoService.crearPedido(pedido);
            return ResponseEntity.status(201).body(nuevo); // 201 Created
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Error al crear pedido: " + e.getMessage()); // 400 Bad Request
        }
    }
    
    // PUT /api/v1/pedidos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Pedido pedido) {
        try {
            Pedido actualizado = pedidoService.updatePedido(id, pedido);
            return ResponseEntity.ok(actualizado); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Pedido no encontrado para actualizar con ID: " + id); // 404 Not Found
        }
    }

    // DELETE /api/v1/pedidos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            pedidoService.deletePedido(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Pedido no encontrado para eliminar con ID: " + id); // 404 Not Found
        }
    }
}