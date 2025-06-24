package com.AgroTech.Seguimiento.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

import com.AgroTech.model.SeguimientoProducto;
import com.AgroTech.repository.SeguimientoProductoRepository;
import com.AgroTech.service.SeguimientoProductoService;

class SeguimientoProductoServiceTest {

    @Mock
    private SeguimientoProductoRepository seguimientoProductoRepository;

    @InjectMocks
    private SeguimientoProductoService seguimientoProductoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarSeguimiento() {
        SeguimientoProducto seguimiento = new SeguimientoProducto();
        when(seguimientoProductoRepository.save(seguimiento)).thenReturn(seguimiento);

        SeguimientoProducto result = seguimientoProductoService.save(seguimiento);

        assertEquals(seguimiento, result);
    }

    @Test
    void testObtenerTodosSeguimientos() {
        List<SeguimientoProducto> lista = Arrays.asList(new SeguimientoProducto(), new SeguimientoProducto());
        when(seguimientoProductoRepository.findAll()).thenReturn(lista);

        List<SeguimientoProducto> result = seguimientoProductoService.getAllSeguimientoProductos();

        assertEquals(2, result.size());
    }

    @Test
    void testObtenerSeguimientoPorId() {
        SeguimientoProducto seguimiento = new SeguimientoProducto();
        seguimiento.setId(1L);
        when(seguimientoProductoRepository.findById(1L)).thenReturn(Optional.of(seguimiento));

        SeguimientoProducto result = seguimientoProductoService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testEliminarSeguimiento() {
        Long id = 1L;

        seguimientoProductoService.delete(id);

        verify(seguimientoProductoRepository, times(1)).deleteById(id);
    }
}
