package com.AgroTech.Entregas.controller;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.AgroTech.Entregas.model.Entrega;
import com.AgroTech.Entregas.service.EntregaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EntregaControllerTest.class)
public class EntregaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EntregaService entregaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearEntrega() throws Exception {
        // Simulamos la petición JSON entrante
        String estado = "Pendiente";
        Date fecha = new Date();

        Entrega creada = new Entrega(10L, null, estado, fecha); // suponiendo que dirección no es parte del método crear

        // Simula comportamiento del servicio
        when(entregaService.crear(any(String.class), any(Date.class))).thenReturn(creada);

        // Representación JSON manual de los parámetros
        String jsonPayload = """
        {
            "estado": "Pendiente",
            "fechaEstimada": "%d"
        }
        """.formatted(fecha.getTime());

        mockMvc.perform(post("/entregas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.estado").value("Pendiente"));
    }
}