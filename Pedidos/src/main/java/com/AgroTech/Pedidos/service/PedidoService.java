package com.AgroTech.Pedidos.service;

import org.springframework.stereotype.Service;

import com.AgroTech.Pedidos.model.Pedidos;
import com.AgroTech.Pedidos.repository.PedidoRepository;
import com.AgroTech.Pedidos.webclient.ClienteClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteClient clienteClient;

    public PedidoService(PedidoRepository pedidoRepository, ClienteClient clienteClient) {
        this.pedidoRepository = pedidoRepository;
        this.clienteClient = clienteClient;
    }

    public Flux<Pedidos> getAllPedidos(){
        return pedidoRepository.findAll();
    }

    public Mono<Pedidos> getPedidoById(Integer id){
        return pedidoRepository.findById(id);
    }

    public Mono<Pedidos> savePedido(Pedidos pedido){
        return pedidoRepository.save(pedido);
    }
}
