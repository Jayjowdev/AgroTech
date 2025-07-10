package com.AgroTech.Incidencias.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ProductoClient {

    private final WebClient webClient;

    public ProductoClient(@Value("${producto-service.url}") String productoServiceUrl){
        this.webClient=WebClient.builder()
                .baseUrl(productoServiceUrl)
                .build();
    }

    public Map<String, Object> getProductoById(String id){
        return webClient.get()
                .uri("/producto/{id}", id)
                .retrieve()
                .onStatus(
                        Status -> Status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException("Entrega no encontrada (ID: " + id + ")"))
                )
                .bodyToMono(Map.class)
                .block();
    }
}

