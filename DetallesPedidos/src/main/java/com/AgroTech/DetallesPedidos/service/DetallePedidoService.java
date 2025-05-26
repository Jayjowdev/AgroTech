package com.AgroTech.DetallesPedidos.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.DetallesPedidos.model.DetallePedido;
import com.AgroTech.DetallesPedidos.repository.DetallePedidoRepository;
import com.AgroTech.DetallesPedidos.webclient.DetallePedidoClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DetallePedidoService {
    
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private DetallePedidoClient detallePedidoClient;

    public List <DetallePedido> findAll(){
        return detallePedidoRepository.findAll();
    }
    
    public DetallePedido findById(Long detallePedidoId) {
        return detallePedidoRepository.findById(detallePedidoId)
                .orElseThrow(() -> new RuntimeException("DetallePedido no encontrado por id: " + detallePedidoId));
    }

    public DetallePedido save(DetallePedido detallePedido) {
        Map<String, Object> pedido = detallePedidoClient.getPedidoById(detallePedido.getPedidoId());
        if (pedido == null || pedido.isEmpty()) {
            throw new RuntimeException("Pedido no encontrado para el ID: " + detallePedido.getPedidoId());
        }
        Map<String, Object> producto = detallePedidoClient.getProductoById(detallePedido.getProductoId());
        if (producto == null || producto.isEmpty()) {
            throw new RuntimeException("Producto no encontrado para el ID: " + detallePedido.getProductoId());
        }
        return detallePedidoRepository.save(detallePedido);
    }

    
    
}


