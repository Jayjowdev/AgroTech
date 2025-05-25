package com.AgroTech.Pedidos.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ClienteClient {
    
    private final WebClient webClient;

    public ClienteClient(@Value ("${cliente-service.url}") String clienteServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(clienteServiceUrl)
                .build();
    }

    //devuelve un Map<String, Object>, es decir, una estructura tipo 
    //clave:valor que representa los datos JSON del cliente.
    public Map <String, Object> getClienteById(Long id) {
        //vamos a hacer una solicitud HTTP de tipo GET
        //retrieve --> ejecuta la solicitud y obtén la respuesta
        //bodyToMono --ZAquí transformas el cuerpo de la respuesta en un objeto Java
        //es un objeto de programación reactiva que representa un solo valor futuro
        //block --> espera aquí hasta que llegue la respuesta
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