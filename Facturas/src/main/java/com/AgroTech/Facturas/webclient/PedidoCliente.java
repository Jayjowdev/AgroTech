package com.AgroTech.Facturas.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PedidoCliente {

    private final WebClient webClient;

    public PedidoCliente(@Value("${pedido-service.url}") String pedidoServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(pedidoServiceUrl)
                .build();
    }

    public Map<String, Object> getPedidoById(String idPedido) {
        return webClient.get()
                .uri("/pedidos/{id}", idPedido)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException("Pedido no encontrado (ID: " + idPedido + ")"))
                )
                .bodyToMono(Map.class)
                .block();
    }


    
}
