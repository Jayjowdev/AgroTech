package com.AgroTech.Incidencias.service;

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

import com.AgroTech.model.Incidencia;
import com.AgroTech.repository.IncidenciaRepository;
import com.AgroTech.service.IncidenciaService;

class IncidenciaServiceTest {

    @InjectMocks
    private IncidenciaService incidenciaService;

    @Mock
    private IncidenciaRepository incidenciaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarIncidencia() {
        Incidencia incidencia = new Incidencia();
        Long solicitudId = 1L; // Add this line to define solicitudId
        when(incidenciaRepository.save(incidencia)).thenReturn(incidencia);

        Incidencia resultado = incidenciaService.crearIncidencia(incidencia, solicitudId);

        assertNotNull(resultado);
        verify(incidenciaRepository, times(1)).save(incidencia);
    }

    @Test
    void testObtenerIncidenciaPorId() {
        Incidencia incidencia = new Incidencia();
        incidencia.setId(1L);
        when(incidenciaRepository.findById(1L)).thenReturn(Optional.of(incidencia));

        Incidencia resultado = incidenciaService.obtenerIncidenciaPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void testListarTodasLasIncidencias() {
        List<Incidencia> lista = Arrays.asList(new Incidencia(), new Incidencia());
        when(incidenciaRepository.findAll()).thenReturn(lista);

        List<Incidencia> resultado = incidenciaService.obtenerTodasIncidencias();

        assertEquals(2, resultado.size());
        verify(incidenciaRepository).findAll();
    }
}
