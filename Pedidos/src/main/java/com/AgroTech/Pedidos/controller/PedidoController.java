package com.AgroTech.Pedidos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AgroTech.Pedidos.model.Pedidos;
import com.AgroTech.Pedidos.service.PedidoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public Flux <Pedidos> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    @GetMapping("/{id}")
    public Mono<Pedidos> getPedidosById(@PathVariable Integer id) {
        return pedidoService.getPedidoById(id);
    }

    @PostMapping
    public Mono<Pedidos> createPedido(@RequestBody Pedidos pedido)
    {
        return pedidoService.savePedido(pedido);
    }   



}
