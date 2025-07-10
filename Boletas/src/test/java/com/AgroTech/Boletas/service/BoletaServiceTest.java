package com.AgroTech.Boletas.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.AgroTech.Boletas.model.Boleta;
import com.AgroTech.Boletas.repository.BoletaRepository;

@ExtendWith(MockitoExtension.class)
public class BoletaServiceTest {

    @Mock
    private BoletaRepository repository;

    @InjectMocks
    private BoletaService service;

    @Test
    void testObtenerTodasLasBoletas() {
        Boleta b1 = new Boleta(1L, 5, 100.0, 500.0, new Date());
        Boleta b2 = new Boleta(2L, 2, 50.0, 100.0, new Date());

        when(repository.findAll()).thenReturn(Arrays.asList(b1, b2));

        List<Boleta> resultado = service.findAll();
        assertThat(resultado).hasSize(2);
        assertThat(resultado).contains(b1, b2);
    }

    @Test
    void testGuardarBoleta() {
        Boleta nueva = new Boleta(null, 3, 70.0, 210.0, new Date());
        Boleta guardada = new Boleta(10L, 3, 70.0, 210.0, new Date());

        when(repository.save(nueva)).thenReturn(guardada);

        Boleta resultado = service.save(nueva);
        assertThat(resultado.getId()).isEqualTo(10L);
        assertThat(resultado.getCantidadProducto()).isEqualTo(3);
    }
}