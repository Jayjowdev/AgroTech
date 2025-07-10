package com.AgroTech.Entregas.service;

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

import com.AgroTech.Entregas.model.Entrega;
import com.AgroTech.Entregas.repository.EntregaRepository;

@ExtendWith(MockitoExtension.class)
public class EntregaServiceTest {

    @Mock
    private EntregaRepository repository;

    @InjectMocks
    private EntregaService service;

    @Test
    
    void testObtenerTodasLasEntregas() {
        Entrega e1 = new Entrega(1L, "Santiago", "Entregado", new Date());
        Entrega e2 = new Entrega(2L, "Valparaíso", "En camino", new Date());

        when(repository.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<Entrega> resultado = service.obtenerEntregas();
        assertThat(resultado).hasSize(2);
        assertThat(resultado).contains(e1, e2);
    }

    @Test
    void testGuardarEntrega() {
        Entrega nueva = new Entrega(null, "Rancagua", "Pendiente", new Date());
        Entrega guardada = new Entrega(10L, "Rancagua", "Pendiente", new Date());

        when(repository.save(nueva)).thenReturn(guardada);

        Entrega resultado = service.crear("Pendiente", new Date());

        assertThat(resultado.getId()).isEqualTo(10L);
        assertThat(resultado.getDireccionEntrega()).isEqualTo("Rancagua");
    }
}