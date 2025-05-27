package com.AgroTech.Pedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping
    public ResponseEntity<?> createPedido(@RequestBody Pedidos pedido) {
        try {
            Pedidos savedPedido = pedidoService.save(pedido);
            return ResponseEntity.status(201).body(savedPedido);
        } catch (RuntimeException  e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity<?> deletePedido(@RequestBody Long pedidoId) {
        try {
            pedidoService.delete(pedidoId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
