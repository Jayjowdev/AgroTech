package com.AgroTech.DetallesPedidos.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ProductoClient {

    private final WebClient webClient;

    public ProductoClient(@Value("${producto-service.url}") String productoServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(productoServiceUrl)
                .build();
    }

    public Map<String, Object> getProductoById(Long id) {
        return this.webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
}