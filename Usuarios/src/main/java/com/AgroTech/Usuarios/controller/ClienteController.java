package com.AgroTech.Usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AgroTech.Usuarios.model.Cliente;
import com.AgroTech.Usuarios.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {    
    @Autowired
    private ClienteService clienteService;

    //endpoint para buscar todos los clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerClientes(){
        List<Cliente> lista = clienteService.getClientes();
        if(lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    //end para obtener un cliente mediante su id
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClienteId(@PathVariable Long id){
        try{
            Cliente nuevo = clienteService.getClienteId(id);
            return ResponseEntity.ok(nuevo);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //endpoing para agregar nuevos clientes
    @PostMapping
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente){
        return ResponseEntity.status(201).body(clienteService.saveCliente(cliente));
    }

}