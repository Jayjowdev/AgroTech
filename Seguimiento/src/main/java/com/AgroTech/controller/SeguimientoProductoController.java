package com.AgroTech.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AgroTech.model.SeguimientoProducto;
import com.AgroTech.service.SeguimientoProductoService;

@RestController
@RequestMapping("/api/seguimientos")
public class SeguimientoProductoController {
    
    private final SeguimientoProductoService seguimientoProductoService;

    public SeguimientoProductoController(SeguimientoProductoService seguimientoProductoService){
        this.seguimientoProductoService = seguimientoProductoService;
    }

    @PostMapping
    public ResponseEntity<SeguimientoProducto> crear(@RequestBody SeguimientoProducto seguimientoProducto) {
        SeguimientoProducto creado = seguimientoProductoService.save(seguimientoProducto);
        return ResponseEntity.ok(creado);
    }

    @GetMapping
    public ResponseEntity<List<SeguimientoProducto>> ListarTodos(){
        return ResponseEntity.ok(seguimientoProductoService.getAllSeguimientoProductos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<SeguimientoProducto> obtenerPorId(@PathVariable Long id) {
        SeguimientoProducto seguimiento = seguimientoProductoService.findById(id);
        return ResponseEntity.ok(seguimiento);
}

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<SeguimientoProducto> obtenerPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(seguimientoProductoService.findByCodigo(codigo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeguimientoProducto> actualizar(@PathVariable Long id, @RequestBody SeguimientoProducto seguimientoActualizado) {
        return ResponseEntity.ok(seguimientoProductoService.update(id, seguimientoActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        seguimientoProductoService.delete(id);
        return ResponseEntity.noContent().build();
    }

       
}

