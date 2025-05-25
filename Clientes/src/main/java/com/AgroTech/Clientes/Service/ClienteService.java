package com.AgroTech.Clientes.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.Clientes.Model.Cliente;
import com.AgroTech.Clientes.Repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado (ID: " + id + ")"));
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
