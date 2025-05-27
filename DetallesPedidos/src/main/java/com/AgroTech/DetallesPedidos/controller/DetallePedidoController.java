package com.AgroTech.DetallesPedidos.controller;

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

import com.AgroTech.DetallesPedidos.model.DetallePedido;
import com.AgroTech.DetallesPedidos.service.DetallePedidoService;

@RestController
@RequestMapping("/api/v1/detalle-pedido")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping
    public ResponseEntity<List<DetallePedido>> getAllDetallePedidos() {
        List<DetallePedido> lista = detallePedidoService.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createDetallePedido(@RequestBody DetallePedido detallePedido) {
    try{
        DetallePedido savedDetallePedido = detallePedidoService.save(detallePedido);
        return ResponseEntity.status(201).body(savedDetallePedido);
    } catch (RuntimeException e) {
        return ResponseEntity.status(400).body("Error al crear el detalle del pedido: " + e.getMessage());
    }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDetallePedido(@PathVariable Long detallePedidoId) {
        try {
            detallePedidoService.delete(detallePedidoId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
