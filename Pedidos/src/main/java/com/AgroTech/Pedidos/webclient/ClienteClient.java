package com.AgroTech.Pedidos.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ClienteClient {

    private final WebClient webClient;

    public ClienteClient(@Value("${cliente-service.url}") String ClienteServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(ClienteServiceUrl)
                .build();
    }

    public Map<String, Object> getClienteById(Integer id) {
        return webClient.get()
                .uri("/clientes/{id}", id)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
}
