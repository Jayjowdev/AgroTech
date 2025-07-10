package com.AgroTech.Pedidos.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.AgroTech.Pedidos.model.Pedido;
import com.AgroTech.Pedidos.model.Pedido.EstadoPedido;
import com.AgroTech.Pedidos.repository.PedidoRepository;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    void testObtenerTodosLosPedidos() {
        Pedido p1 = new Pedido(1L, 100L, new Date(), EstadoPedido.ENVIADO, Collections.emptyList());
        Pedido p2 = new Pedido(2L, 101L, new Date(), EstadoPedido.ENTREGADO, Collections.emptyList());

        when(pedidoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Pedido> resultado = pedidoService.findAll();
        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getEstado()).isEqualTo("PENDIENTE");
    }

    @Test
    void testGuardarPedido() {
        Pedido nuevo = new Pedido(null, 123L, new Date(), EstadoPedido.ENVIADO, Collections.emptyList());
        Pedido guardado = new Pedido(10L, 123L, new Date(), EstadoPedido.EN_PROCESO, Collections.emptyList());

        when(pedidoRepository.save(nuevo)).thenReturn(guardado);

        Pedido resultado = pedidoService.crearPedido(guardado);
        assertThat(resultado.getId()).isEqualTo(10L);
        assertThat(resultado.getEstado()).isEqualTo("EN_TRANSITO");
    }
}