package com.AgroTech.Incidencias.controller;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.AgroTech.Incidencias.model.Incidencia;
import com.AgroTech.Incidencias.service.IncidenciaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(IncidenciaController.class)
public class IncidenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IncidenciaService incidenciaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObtenerIncidencias() throws Exception {
        Incidencia i1 = new Incidencia(1L, "ABC123", new Date(), Incidencia.EstadoIncidencia.ABIERTA,Incidencia.EstadoMaquina.MANTENIMIENTO);
        Incidencia i2 = new Incidencia(2L, "XYZ789", new Date(), Incidencia.EstadoIncidencia.CERRADA, Incidencia.EstadoMaquina.OPERATIVA);

        when(incidenciaService.findAll()).thenReturn(Arrays.asList(i1, i2));

        mockMvc.perform(get("/incidencias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGuardarIncidencia() throws Exception {
        Incidencia nueva = new Incidencia(null, "ZZZ456", new Date(), Incidencia.EstadoIncidencia.ABIERTA, Incidencia.EstadoMaquina.MANTENIMIENTO);
        Incidencia guardada = new Incidencia(10L, "ZZZ456", new Date(), Incidencia.EstadoIncidencia.CERRADA, Incidencia.EstadoMaquina.OPERATIVA);

        when(incidenciaService.saveIncidencia(any(Incidencia.class))).thenReturn(guardada);

        mockMvc.perform(post("/incidencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nueva)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.serialMaquina").value("ZZZ456"))
                .andExpect(jsonPath("$.estadoIncidencia").value("ABIERTA"));
    }
}