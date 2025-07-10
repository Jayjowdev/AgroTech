package com.AgroTech.Pedidos.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.AgroTech.Pedidos.model.Pedido;
import com.AgroTech.Pedidos.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(PedidoControllerTest.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObtenerPedidos() throws Exception {
        Pedido p1 = new Pedido(1L, 100L, new Date(), Pedido.EstadoPedido.EN_PROCESO, Collections.emptyList());
        Pedido p2 = new Pedido(2L, 101L, new Date(), Pedido.EstadoPedido.ENVIADO, Collections.emptyList());

        when(pedidoService.findAll()).thenReturn(Arrays.asList(p1, p2));

        mockMvc.perform(get("/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGuardarPedido() throws Exception {
        Pedido nuevo = new Pedido(null, 123L, new Date(), Pedido.EstadoPedido.EN_PROCESO, Collections.emptyList());
        Pedido guardado = new Pedido(10L, 123L, new Date(), Pedido.EstadoPedido.ENVIADO, Collections.emptyList());

        when(pedidoService.crearPedido(any(Pedido.class))).thenReturn(guardado);

        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.estado").value("EN_TRANSITO"));
    }
}