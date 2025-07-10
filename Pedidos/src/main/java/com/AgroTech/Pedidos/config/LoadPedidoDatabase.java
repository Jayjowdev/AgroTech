package com.AgroTech.Pedidos.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.AgroTech.Pedidos.model.DetallePedido;
import com.AgroTech.Pedidos.model.Pedido;
import com.AgroTech.Pedidos.repository.PedidoRepository;

@Configuration
public class LoadPedidoDatabase {
    @Bean
    CommandLineRunner initDatabase(PedidoRepository pedidoRepo){
     return args -> {
            if (pedidoRepo.count() == 0) {
                Pedido pedido1 = new Pedido();
                pedido1.setUsuarioId(1L);
                pedido1.setFechaEstimada(new Date());
                pedido1.setEstado(Pedido.EstadoPedido.EN_PROCESO);

                DetallePedido d1 = new DetallePedido(null, 2L, 3, pedido1);
                DetallePedido d2 = new DetallePedido(null, 5L, 1, pedido1);
                pedido1.setDetalles(Arrays.asList(d1, d2));

                Pedido pedido2 = new Pedido();
                pedido2.setUsuarioId(2L);
                pedido2.setFechaEstimada(new Date());
                pedido2.setEstado(Pedido.EstadoPedido.ENVIADO);

                DetallePedido d3 = new DetallePedido(null, 3L, 2, pedido2);
                pedido2.setDetalles(Arrays.asList(d3));

                pedidoRepo.saveAll(Arrays.asList(pedido1, pedido2));

                System.out.println("Pedidos precargados.");
            } else {
                System.out.println("Pedidos ya existen. No se cargan nuevos.");
            }
        };
    }
}