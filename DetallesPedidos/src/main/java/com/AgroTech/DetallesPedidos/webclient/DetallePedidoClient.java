package com.AgroTech.DetallesPedidos.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class DetallePedidoClient {

    private final WebClient pedidoWebClient;
    private final WebClient productoWebClient;

    public DetallePedidoClient(
        @Value("${pedido.service.url}") String pedidoServiceUrl,
        @Value("${producto.service.url}") String productoServiceUrl
    ) {
        this.pedidoWebClient = WebClient.builder()
            .baseUrl(pedidoServiceUrl)
            .build();
        this.productoWebClient = WebClient.builder()
            .baseUrl(productoServiceUrl)
            .build();
    }

    public Map<String, Object> getPedidoById(Long idPedido) {
        return pedidoWebClient.get()
            .uri("/pedidos/{id}", idPedido)
            .retrieve()
            .onStatus(
                        status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException("Pedido no encontrado (ID: " + idPedido + ")"))
                    )
            .bodyToMono(Map.class)
            .block();
    }

    public Map<String, Object> getProductoById(Long productoId) {
        return productoWebClient.get()
            .uri("/productos/{id}", productoId)
            .retrieve()
            .onStatus(
                        status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new RuntimeException("Producto no encontrado (ID: " + productoId + ")"))
                    )
            .bodyToMono(Map.class)
            .block();
    }
}
