package com.AgroTech.Clientes.Controller;

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

import com.AgroTech.Clientes.Model.Cliente;
import com.AgroTech.Clientes.Service.ClienteService;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

        @Autowired
        private ClienteService clienteService;

        @GetMapping("/Cliente")
        public ResponseEntity <List<Cliente>> getAllClientes(){
            List<Cliente > lista2 = clienteService.findAll();
            if (lista2.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(lista2);
        }

        @GetMapping("/Clienteid")
        public ResponseEntity<Cliente> getClienteById(@PathVariable Long id){
            try{
                Cliente Cliente = clienteService.findById(id);
                return ResponseEntity.ok(Cliente);
            }
            catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping("/{Clienteid}")
        public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){
            return ResponseEntity.status(201).body(clienteService.save(cliente));
        }


        @DeleteMapping("/{Clienteid}")
        public ResponseEntity<?> deleteCliente(@PathVariable Long Clienteid) {
            try {
                clienteService.delete(Clienteid);
                return ResponseEntity.noContent().build();
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
        }

}
