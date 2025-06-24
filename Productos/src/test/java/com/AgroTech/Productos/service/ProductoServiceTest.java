
package com.AgroTech.Productos.service;

import com.AgroTech.Productos.model.Producto;
import com.AgroTech.Productos.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerProductoPorId() {
        Producto producto = new Producto();
        producto.setIdProducto(1L);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto result = productoService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdProducto());
    }
}
