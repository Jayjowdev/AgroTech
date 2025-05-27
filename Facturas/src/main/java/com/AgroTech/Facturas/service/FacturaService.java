package com.AgroTech.Facturas.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.Facturas.model.Factura;
import com.AgroTech.Facturas.repository.FacturaRepository;
import com.AgroTech.Facturas.webclient.PedidoCliente;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private PedidoCliente PedidoCliente;

    public List<Factura> findAll(){
        return facturaRepository.findAll();
    }

    public Factura findByFacturaId(Long FacturaId){
        return facturaRepository.findById(FacturaId)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada (ID: " + FacturaId + ")"));
    }

    public Factura save(Factura factura){
        Map<String, Object> pedido = PedidoCliente.getPedidoById(factura.getIdPedido());
        if (pedido == null || pedido.isEmpty()) {
            throw new RuntimeException("Pedido no encontrado (ID: " + factura.getIdPedido() + ")");
        }
        return facturaRepository.save(factura);
    }

    public void delete(Long facturaId) {
        if (!facturaRepository.existsById(facturaId)) {
            throw new RuntimeException("Factura no encontrada (ID: " + facturaId + ")");
        }
        facturaRepository.deleteById(facturaId);
    }
}
