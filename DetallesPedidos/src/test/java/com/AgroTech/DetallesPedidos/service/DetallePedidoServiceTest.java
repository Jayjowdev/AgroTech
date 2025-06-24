package com.AgroTech.DetallesPedidos.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.AgroTech.DetallesPedidos.model.DetallePedido;
import com.AgroTech.DetallesPedidos.repository.DetallePedidoRepository;

public class DetallePedidoServiceTest {

    @Mock
    private DetallePedidoRepository detallePedidoRepository;

    @InjectMocks
    private DetallePedidoService detallePedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarDetallePedido() {
        DetallePedido detalle = new DetallePedido();
        when(detallePedidoRepository.save(detalle)).thenReturn(detalle);

        DetallePedido result = detallePedidoService.guardarDetallePedido(detalle);

        assertEquals(detalle, result);
        verify(detallePedidoRepository).save(detalle);
    }

    @Test
    void testObtenerDetallePedidoPorId() {
        DetallePedido detalle = new DetallePedido();
        detalle.setIdDetallePedido(1L);
        when(detallePedidoRepository.findById(1L)).thenReturn(Optional.of(detalle));

        Optional<DetallePedido> result = detallePedidoService.obtenerDetallePedidoPorId(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getIdDetallePedido());
    }
}
