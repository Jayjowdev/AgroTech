package com.AgroTech.Pedidos.controller;

import com.AgroTech.Pedidos.model.Pedidos;
import com.AgroTech.Pedidos.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Test
    public void testObtenerTodos() throws Exception {
        Pedidos pedido = new Pedidos();
        when(pedidoService.findAll()).thenReturn(Arrays.asList(pedido));

        mockMvc.perform(MockMvcRequestBuilders.get("/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void testGuardarPedido() throws Exception {
        Pedidos pedido = new Pedidos();
        when(pedidoService.save(any(Pedidos.class))).thenReturn(pedido);

        mockMvc.perform(MockMvcRequestBuilders.post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testEliminarPedido() throws Exception {
        Long id = 1L;
        doNothing().when(pedidoService).delete(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/pedidos/" + id))
                .andExpect(status().isOk());
    }
}