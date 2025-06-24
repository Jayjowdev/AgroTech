package com.AgroTech.Pedidos.service;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.AgroTech.Pedidos.model.Pedidos;
import com.AgroTech.Pedidos.repository.PedidoRepository;

public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    public PedidoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarPedido() {
        Pedidos pedido = new Pedidos();
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedidos resultado = pedidoService.save(pedido);
        assertEquals(pedido, resultado);
    }

    @Test
    public void testObtenerTodos() {
        List<Pedidos> lista = Arrays.asList(new Pedidos(), new Pedidos());
        when(pedidoRepository.findAll()).thenReturn(lista);

        List<Pedidos> resultado = pedidoService.findAll();
        assertEquals(2, resultado.size());
    }

    @Test
    public void testEliminarPedido() {
        Long id = 1L;
        pedidoService.delete(id);
        verify(pedidoRepository, times(1)).deleteById(id);
    }
}