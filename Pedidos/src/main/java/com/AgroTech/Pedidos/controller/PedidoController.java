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

import com.AgroTech.Pedidos.model.Pedidos;
import com.AgroTech.Pedidos.service.PedidoService;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    
    @GetMapping
    public ResponseEntity<List<Pedidos>> getAllPedidos() {
        List<Pedidos> Lista = pedidoService.findAll();
        if(Lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(Lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> getPedidoById(@PathVariable Long id) {
    Pedidos pedido = pedidoService.findById(id);

    if (pedido != null) {
        return ResponseEntity.ok(pedido);
    } else {
        return ResponseEntity.notFound().build();
    }
    }

    @PostMapping("/{Id}")
    public ResponseEntity<?> createPedido(@RequestBody Pedidos pedido) {
        try {
            Pedidos savedPedido = pedidoService.save(pedido);
            return ResponseEntity.status(201).body(savedPedido);
        } catch (RuntimeException  e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedidos> actualizar(@PathVariable Long id, @RequestBody Pedidos pedido) {
        return ResponseEntity.ok(pedidoService.update(id, pedido));
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<?> deletePedido(@RequestBody Long pedidoId) {
        try {
            pedidoService.delete(pedidoId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
