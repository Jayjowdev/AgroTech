package com.AgroTech.Entregas.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.Entregas.Model.Entrega;
import com.AgroTech.Entregas.Repository.EntregaRepository;
import com.AgroTech.Entregas.webclient.PedidoClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private PedidoClient pedidoClient;

    public List<Entrega> findAll() {
        return entregaRepository.findAll();
    }

    public Entrega findByEntregaId(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada (ID: " + entregaId + ")"));
    }
    
    public Entrega save(Entrega entrega) {
        
        Map<String, Object> pedido = pedidoClient.getPedidoById(entrega.getIdPedido());
        if (pedido == null || pedido.isEmpty()) {
            throw new RuntimeException("Pedido no encontrado (ID: " + entrega.getIdPedido() + ")");
        }
        return entregaRepository.save(entrega);
    }

    public void delete(Long entregaId) {
        if (!entregaRepository.existsById(entregaId)) {
            throw new RuntimeException("Entrega no encontrada (ID: " + entregaId + ")");
        }
        entregaRepository.deleteById(entregaId);
    }

}
