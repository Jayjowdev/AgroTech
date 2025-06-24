
package com.AgroTech.Entregas.controller;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.AgroTech.Entregas.Controller.EntregaController;
import com.AgroTech.Entregas.Model.Entrega;
import com.AgroTech.Entregas.Service.EntregaService;

public class EntregaControllerTest {

    @Mock
    private EntregaService entregaService;

    @InjectMocks
    private EntregaController entregaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearEntrega() {
        Entrega entrega = new Entrega();
        entrega.setEstado("EN_TRANSITO");

        when(entregaService.save(entrega)).thenReturn(entrega);

        ResponseEntity<?> response = entregaController.createEntrega(entrega);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(entrega, response.getBody());
    }

    @Test
    public void testObtenerEntregaPorId() {
        Entrega entrega = new Entrega();
        entrega.setIdEntrega(1L);

        when(entregaService.buscarEntregaPorId(1L)).thenReturn(Optional.of(entrega));

        ResponseEntity<?> response = entregaController.getEntregaById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(entrega, response.getBody());
    }
}
