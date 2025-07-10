package com.AgroTech.Seguimientos.controller;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.AgroTech.Seguimientos.model.Seguimiento;
import com.AgroTech.Seguimientos.model.Seguimiento.EstadoSeguimiento;
import com.AgroTech.Seguimientos.service.SeguimientoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(SeguimientoController.class)
public class SeguimientoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeguimientoService seguimientoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObtenerSeguimientos() throws Exception {
        Seguimiento s1 = new Seguimiento(1L, "Bodega Central", new Date(), EstadoSeguimiento.EN_TRANSITO);
        Seguimiento s2 = new Seguimiento(2L, "Sucursal Norte", new Date(), EstadoSeguimiento.ENTREGADO);

        when(seguimientoService.findAll()).thenReturn(Arrays.asList(s1, s2));

        mockMvc.perform(get("/seguimientos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGuardarSeguimiento() throws Exception {
        Seguimiento nuevo = new Seguimiento(null, "Peaje Norte", new Date(), EstadoSeguimiento.PENDIENTE);
        Seguimiento guardado = new Seguimiento(10L, "Peaje Norte", new Date(), EstadoSeguimiento.PENDIENTE);

        when(seguimientoService.SaveSeguimiento(any(Seguimiento.class))).thenReturn(guardado);

        mockMvc.perform(post("/seguimientos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.ubicacionActual").value("Peaje Norte"));
    }
}


