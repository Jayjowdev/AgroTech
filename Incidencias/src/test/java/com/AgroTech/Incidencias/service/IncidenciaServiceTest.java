package com.AgroTech.Incidencias.service;

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

import com.AgroTech.Incidencias.model.Incidencia;
import com.AgroTech.Incidencias.model.Incidencia.EstadoIncidencia;
import com.AgroTech.Incidencias.model.Incidencia.EstadoMaquina;
import com.AgroTech.Incidencias.repository.IncidenciaRepository;

@ExtendWith(MockitoExtension.class)
public class IncidenciaServiceTest {

    @Mock
    private IncidenciaRepository repository;

    @InjectMocks
    private IncidenciaService service;

    @Test
    void testObtenerTodasLasIncidencias() {
        Incidencia i1 = new Incidencia(1L, "ABC123", new Date(), EstadoIncidencia.ABIERTA, EstadoMaquina.MANTENIMIENTO);
        Incidencia i2 = new Incidencia(2L, "XYZ789", new Date(), EstadoIncidencia.CERRADA, EstadoMaquina.OPERATIVA);

        when(repository.findAll()).thenReturn(Arrays.asList(i1, i2));

        List<Incidencia> resultado = service.findAll();
        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getSerialMaquina()).isEqualTo("ABC123");
    }

    @Test
    void testGuardarIncidencia() {
        Incidencia nueva = new Incidencia(null, "ZZZ456", new Date(), EstadoIncidencia.ABIERTA, EstadoMaquina.MANTENIMIENTO);
        Incidencia guardada = new Incidencia(10L, "ZZZ456", new Date(), EstadoIncidencia.CERRADA, EstadoMaquina.OPERATIVA);

        when(repository.save(nueva)).thenReturn(guardada);

        Incidencia resultado = service.saveIncidencia(nueva);
        assertThat(resultado.getId()).isEqualTo(10L);
        assertThat(resultado.getSerialMaquina()).isEqualTo("ZZZ456");
    }
}