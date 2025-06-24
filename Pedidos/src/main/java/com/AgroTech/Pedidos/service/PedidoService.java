package com.AgroTech.Pedidos.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.Pedidos.model.Pedidos;
import com.AgroTech.Pedidos.repository.PedidoRepository;
import com.AgroTech.Pedidos.webclient.ProductoClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoClient productoClient;

    public List<Pedidos> findAll(){
        return pedidoRepository.findAll();
    }

    public Pedidos findById(Long id){
        return pedidoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Pedido no encontrado (ID: "+ id +")"));
    }

    public Pedidos save(Pedidos pedido){
        Map<String, Object> producto = productoClient.getProductoById(String.valueOf(pedido.getProductoId()));
        if (producto==null || producto.isEmpty()) {
            throw new RuntimeException("Producto no encontrado (ID: " + pedido.getProductoId() + ")");
        }

        return pedidoRepository.save(pedido);
    }
     public Pedidos update(Long id, Pedidos pedidoActualizado) {
        Pedidos pedidoExistente = findById(id);

        pedidoExistente.setCantidad(pedidoActualizado.getCantidad());
        pedidoExistente.setEstado(pedidoActualizado.getEstado());
        pedidoExistente.setFechaPedido(pedidoActualizado.getFechaPedido());

        return pedidoRepository.save(pedidoExistente);
    }

    public void delete(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido no encontrado (ID: " + id + ")");
        }
        pedidoRepository.deleteById(id);
    }
}
