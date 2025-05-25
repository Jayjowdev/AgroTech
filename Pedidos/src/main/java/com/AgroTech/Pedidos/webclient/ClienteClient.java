package com.AgroTech.Pedidos.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ClienteClient {

    private final WebClient webClient;

    public ClienteClient(@Value("${cliente-service.url}") String clienteServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(clienteServiceUrl)
                .build();
    }


    public Map<String, Object> getClienteById(Long id) {
        return this.webClient.get()
            .uri("/{id}", id)
            .retrieve()
            .onStatus(
                status -> status.is4xxClientError(),  // Si es error 4XX
                response -> response.bodyToMono(String.class)
                                     .map(body -> new RuntimeException("Cliente no encontrado (ID: " + id + ")"))
            )
            .bodyToMono(Map.class)
            .block();
    }
}
