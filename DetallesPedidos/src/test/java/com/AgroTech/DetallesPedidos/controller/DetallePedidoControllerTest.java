
package com.AgroTech.DetallesPedidos.controller;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.AgroTech.DetallesPedidos.model.DetallePedido;
import com.AgroTech.DetallesPedidos.service.DetallePedidoService;

public class DetallePedidoControllerTest {

    @Mock
    private DetallePedidoService detallePedidoService;

    @InjectMocks
    private DetallePedidoController detallePedidoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarDetallePedido() {
        DetallePedido detalle = new DetallePedido();
        when(detallePedidoService.guardarDetallePedido(detalle)).thenReturn(detalle);

        DetallePedido result = detallePedidoController.guardarDetallePedido(detalle);

        assertNotNull(result);
        assertEquals(detalle, result);
    }

    @Test
    void testObtenerDetallePedidoPorId() {
        DetallePedido detalle = new DetallePedido();
        detalle.setIdDetallePedido(1L);
        when(detallePedidoService.obtenerDetallePedidoPorId(1L)).thenReturn(Optional.of(detalle));

        ResponseEntity<DetallePedido> response = detallePedidoController.obtenerDetallePedidoPorId(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getIdDetallePedido());
    }
}
