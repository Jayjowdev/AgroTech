package com.AgroTech.Seguimiento.controller;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.AgroTech.controller.SeguimientoProductoController;
import com.AgroTech.model.SeguimientoProducto;
import com.AgroTech.service.SeguimientoProductoService;

class SeguimientoProductoControllerTest {

    @Mock
    private SeguimientoProductoService seguimientoProductoService;

    @InjectMocks
    private SeguimientoProductoController seguimientoProductoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarSeguimiento() {
        SeguimientoProducto seguimiento = new SeguimientoProducto();
        when(seguimientoProductoService.save(seguimiento)).thenReturn(seguimiento);

        ResponseEntity<SeguimientoProducto> response = seguimientoProductoController.crear(seguimiento);
        SeguimientoProducto result = response.getBody();

        assertEquals(seguimiento, result);
    }

    @Test
    void testObtenerTodosSeguimientos() {
        List<SeguimientoProducto> lista = Arrays.asList(new SeguimientoProducto(), new SeguimientoProducto());
        when(seguimientoProductoService.getAllSeguimientoProductos()).thenReturn(lista);

        List<SeguimientoProducto> result = seguimientoProductoController.ListarTodos().getBody();

        assertEquals(2, result.size());
    }

    @Test
    void testObtenerSeguimientoPorId() {
    SeguimientoProducto seguimiento = new SeguimientoProducto();
    seguimiento.setId(1L);

    when(seguimientoProductoService.findById(1L)).thenReturn(seguimiento);
        ResponseEntity<SeguimientoProducto> response = seguimientoProductoController.obtenerPorId(1L);
        SeguimientoProducto result = response.getBody();
            assertNotNull(result);
            assertEquals(1L, result.getId());
}
    @Test
    void testEliminarSeguimiento() {
        ResponseEntity<Void> response = seguimientoProductoController.eliminar(1L);

            assertEquals(204, response.getStatusCodeValue());
            verify(seguimientoProductoService, times(1)).delete(1L);
    }
}
