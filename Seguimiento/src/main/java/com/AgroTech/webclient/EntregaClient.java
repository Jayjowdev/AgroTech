package com.AgroTech.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class EntregaClient {

    private final WebClient webClient;
    
    public EntregaClient(@Value("${entrega-service.url}") String entregaServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(entregaServiceUrl)
                .build();
    }

    public Map<String, Object> getEntregaById(String idEntrega) {
        return webClient.get()
                .uri("/entregas/{id}", idEntrega)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException("Entrega no encontrada (ID: " + idEntrega + ")"))
                )
                .bodyToMono(Map.class)
                .block();
    }


}
