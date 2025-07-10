package com.AgroTech.Usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.Usuarios.model.Cliente;
import com.AgroTech.Usuarios.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    //metodo para buscar todos los clientes
    public List<Cliente> getClientes(){
        return clienteRepository.findAll();
    }

    //metodo para buscar un cliente por su id
    public Cliente getClienteId(long id){
        return clienteRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Cliente no encontrado por su id"));
    }

    //metodo para guardar un cliente
    public Cliente saveCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }
}
