package com.AgroTech.Pedidos.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.Pedidos.model.DetallePedido;
import com.AgroTech.Pedidos.model.Pedido;
import com.AgroTech.Pedidos.repository.DetallePedidoRepository;
import com.AgroTech.Pedidos.repository.PedidoRepository;
import com.AgroTech.Pedidos.webClient.ProductoClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private DetallePedidoRepository  detallePedidoRepo;

     @Autowired
    private ProductoClient productoClient;

    public List<Pedido> findAll() {
        return pedidoRepo.findAll();
    }

    public Pedido getById(Long id) {
        return pedidoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + id));
    }

    public Pedido updatePedido(Long id, Pedido datos) {
        Pedido existente = getById(id);
        existente.setEstado(datos.getEstado());
        existente.setFechaEstimada(datos.getFechaEstimada());
        // No actualizamos detalles aquí para simplificar
    return pedidoRepo.save(existente);
    }

    public void deletePedido(Long id) {
        Pedido pedido = getById(id);
        pedidoRepo.delete(pedido);
    }


    public Pedido crearPedido(Pedido pedido) {
        pedido.setFechaEstimada(new Date());
        pedido.setEstado(Pedido.EstadoPedido.EN_PROCESO);

        // Validar productos con WebClient
        for (DetallePedido detalle : pedido.getDetalles()) {
            Map<String, Object> producto = productoClient.getProductoById(detalle.getProductoId());
            if (producto == null || producto.get("nombre") == null) {
                throw new RuntimeException("Producto inválido con ID: " + detalle.getProductoId());
            }
            detalle.setPedido(pedido); // vincular con el pedido
        }

        return pedidoRepo.save(pedido); // guarda pedido y cascades detalles
    }

    
}