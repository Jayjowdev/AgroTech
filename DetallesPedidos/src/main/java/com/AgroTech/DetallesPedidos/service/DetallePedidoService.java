package com.AgroTech.DetallesPedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.DetallesPedidos.model.DetallePedido;
import com.AgroTech.DetallesPedidos.repository.DetallePedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DetallePedidoService {
    
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public List <DetallePedido> findAll(){
        return detallePedidoRepository.findAll();
    }
    
    public DetallePedido findById(Long detallePedidoId) {
        return detallePedidoRepository.findById(detallePedidoId)
                .orElseThrow(() -> new RuntimeException("DetallePedido no encontrado por id: " + detallePedidoId));
    }

    public DetallePedido save(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }
    
}


