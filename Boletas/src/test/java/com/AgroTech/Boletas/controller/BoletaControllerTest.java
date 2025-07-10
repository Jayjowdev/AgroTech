package com.AgroTech.Boletas.controller;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.AgroTech.Boletas.model.Boleta;
import com.AgroTech.Boletas.service.BoletaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BoletaController.class)
public class BoletaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoletaService boletaService;

    @Autowired
    private  ObjectMapper objectMapper;

    @Test
    void testObtenerBoletas() throws Exception {
        Boleta b1 = new Boleta(1L, 5, 100.0, 500.0, new Date());
        Boleta b2 = new Boleta(2L, 2, 50.0, 100.0, new Date());

        when(boletaService.findAll()).thenReturn(Arrays.asList(b1, b2));

        mockMvc.perform(get("/boletas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGuardarBoleta() throws Exception {
        Boleta nueva = new Boleta(null, 3, 70.0, 210.0, new Date());
        Boleta guardada = new Boleta(10L, 3, 70.0, 210.0, new Date());

        when(boletaService.save(nueva)).thenReturn(guardada);

        mockMvc.perform(post("/boletas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nueva)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.cantidadProducto").value(3));
    }
}

