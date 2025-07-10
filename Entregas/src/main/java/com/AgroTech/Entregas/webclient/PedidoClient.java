package com.AgroTech.Entregas.webclient;

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
    public Map<String, Object> getPedidoById(String id) {
        return webClient.get()
                .uri("/pedidos/{id}", id)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError(),
                         response -> response.bodyToMono(String.class)
                        .map(body -> new RuntimeException("Pedido no encontrado (ID: " + id + ")"))
                )
                .bodyToMono(Map.class)
                .block();
    }
}
