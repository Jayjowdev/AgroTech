package com.AgroTech.Boletas.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PedidoClient {

    private final WebClient webClient;

    public PedidoClient(@Value("${pedido-service.url}") String pedidoServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(pedidoServiceUrl)
                .build();
    }

    public Map<String, Object> getPedidoById(Long id) {
        return this.webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
}
