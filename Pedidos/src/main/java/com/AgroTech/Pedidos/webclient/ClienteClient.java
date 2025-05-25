package com.AgroTech.Pedidos.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Data;
import reactor.core.publisher.Mono;

@Component
public class ClienteClient {

    private final WebClient webClient;

    public ClienteClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    public Mono<ClienteDTO> getClienteById(Integer id) {
        return webClient.get()
                .uri("/clientes/{id}", id)
                .retrieve()
                .bodyToMono(ClienteDTO.class);
    }

    @Data
    public static class ClienteDTO {
        private Integer idCliente;
        private String nombre;
        private String apellido;
        private String direccion;
        private String telefono;
    } 
}
