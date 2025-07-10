package com.AgroTech.Productos.service;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.AgroTech.Productos.model.Producto;
import com.AgroTech.Productos.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository repository;

    @InjectMocks
    private ProductoService service;

    @Test

    void testObtenerProductos() {
        Producto p1 = new Producto(1L, "Tractor", 15900000, 10);
        Producto p2 = new Producto(2L, "Cocechadora", 12500000, 6);

        when(repository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Producto> resultado = service.findAll();
        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getNombre()).isEqualTo("Tomate");
    }

    @Test
    void testGuardarProducto() {
        Producto nuevo = new Producto(1L, "Tractor", 15900000, 10);
        Producto guardado = new Producto(2L, "Cocechadora", 12500000, 6);

        when(repository.save(nuevo)).thenReturn(guardado);

        Producto resultado = service.saveProducto(nuevo);
        assertThat(resultado.getId()).isEqualTo(10L);
        assertThat(resultado.getNombre()).isEqualTo("Lechuga");
    }
}

