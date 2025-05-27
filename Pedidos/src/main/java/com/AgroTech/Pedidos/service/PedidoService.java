package com.AgroTech.Pedidos.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.Pedidos.model.Pedidos;
import com.AgroTech.Pedidos.repository.PedidoRepository;
import com.AgroTech.Pedidos.webclient.ClienteClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteClient ClienteClient;

    public List<Pedidos> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedidos save(Pedidos pedido) {
        // Validar que el cliente exista consultando al cliente-service
        Map<String, Object> cliente = ClienteClient.getClienteById(pedido.getClienteId());
        if (cliente == null || cliente.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado. No se puede guardar el pedido.");
        }
        return pedidoRepository.save(pedido);
    }

    public void delete(Long pedidoId) {
        if (!pedidoRepository.existsById(pedidoId)) {
            throw new RuntimeException("Pedido no encontrado (ID: " + pedidoId + ")");
        }
        pedidoRepository.deleteById(pedidoId);
    }
}

        
