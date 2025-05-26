package com.AgroTech.Productos.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;



@Component
public class ProveedorClient {
    
    private final WebClient webClient;
    
    public ProveedorClient(@Value("${proveedor.service.url}") String proveedorServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(proveedorServiceUrl)
                .build();
    }

    public Map<String, Object> getProveedorById(Long proveedorId) {
        return webClient.get()
                .uri("/proveedores/{id}", proveedorId)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
    
}
