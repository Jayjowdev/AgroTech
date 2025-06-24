package com.AgroTech.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class usuarioClient {

    private final WebClient webClient;

    public usuarioClient(@Value("${pedido-service.url}") String usuarioServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(usuarioServiceUrl)
                .build();
    }
    public Map<String, Object> getUsuarioById(String usuarioId) {
        return webClient.get()
                .uri("/usuario/{id}", usuarioId)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError(),
                         response -> response.bodyToMono(String.class)
                        .map(body -> new RuntimeException("Usuario no encontrado (ID: " + usuarioId + ")"))
                )
                .bodyToMono(Map.class)
                .block();
    }
    
}
