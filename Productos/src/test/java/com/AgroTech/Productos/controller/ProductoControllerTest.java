package com.AgroTech.Productos.controller;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.AgroTech.Productos.model.Producto;
import com.AgroTech.Productos.service.ProductoService;

public class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerProductoPorId() {
        Producto producto = new Producto();
        producto.setIdProducto(1L);
        when(productoService.obtenerProductoPorId(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> result = productoController.obtenerProductoPorId(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getIdProducto());
    }
}
