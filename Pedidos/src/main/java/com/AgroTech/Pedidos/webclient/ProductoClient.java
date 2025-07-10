package com.AgroTech.Pedidos.webClient;

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
   
public Map<String, Object> getProductoById(Long productoId) {
    return webClient.get()
        .uri("/producto/{id}", productoId)
        .retrieve()
        .onStatus(status -> status.is4xxClientError(),
                 response -> response.bodyToMono(String.class)
                        .map(body -> new RuntimeException("Producto no encontrado: " + productoId))
                )
                .bodyToMono(Map.class)
                .block();
    }
}
