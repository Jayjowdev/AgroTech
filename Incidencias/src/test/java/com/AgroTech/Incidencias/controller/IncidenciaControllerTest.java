package com.AgroTech.Incidencias.controller;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.AgroTech.controller.IncidenciaController;
import com.AgroTech.model.Incidencia;
import com.AgroTech.service.IncidenciaService;

class IncidenciaControllerTest {

    @InjectMocks
    private IncidenciaController incidenciaController;

    @Mock
    private IncidenciaService incidenciaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearIncidencia() {
        Incidencia incidencia = new Incidencia();
        Long userId = 1L;
        when(incidenciaService.crearIncidencia(incidencia, userId)).thenReturn(incidencia);

        Incidencia response = incidenciaController.crearIncidencia(incidencia, "testUser");

        assertNotNull(response);
    }

    @Test
    void testObtenerIncidencia() {
        Incidencia incidencia = new Incidencia();
        incidencia.setId(1L);
        when(incidenciaService.obtenerIncidenciaPorId(1L)).thenReturn(incidencia);

        Incidencia response = incidenciaController.obtenerIncidenciaPorId(1L);
        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void testListarIncidencias() {
        List<Incidencia> lista = Arrays.asList(new Incidencia(), new Incidencia());
        when(incidenciaService.obtenerTodasIncidencias()).thenReturn(lista);

        List<Incidencia> response = incidenciaController.obtenerTodasIncidencias();

        assertEquals(2, response.size());
    }
}
