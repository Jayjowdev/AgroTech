package com.AgroTech.Incidencias.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class UsuarioClient {

    private final WebClient webClient;

    public UsuarioClient(@Value("${pedido-service.url}") String usuarioServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(usuarioServiceUrl)
                .build();
    }
    public Map<String, Object> getUsuarioById(String id) {
        return webClient.get()
                .uri("/usuario/{id}", id)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError(),
                         response -> response.bodyToMono(String.class)
                        .map(body -> new RuntimeException("Usuario no encontrado (ID: " + id + ")"))
                )
                .bodyToMono(Map.class)
                .block();
    }
    
}