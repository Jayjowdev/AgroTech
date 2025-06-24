
package com.AgroTech.Entregas.service;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.AgroTech.Entregas.Model.Entrega;
import com.AgroTech.Entregas.Repository.EntregaRepository;
import com.AgroTech.Entregas.Service.EntregaService;


public class EntregaServiceTest {

    @Mock
    private EntregaRepository entregaRepository;

    @InjectMocks
    private EntregaService entregaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarEntrega() {
        Entrega entrega = new Entrega();
        entrega.setIdEntrega(1L);
        entrega.setEstado("EN_TRANSITO");

        when(entregaRepository.save(entrega)).thenReturn(entrega);

        Entrega resultado = entregaService.save(entrega);
        assertNotNull(resultado);
        assertEquals("EN_TRANSITO", resultado.getEstado());
    }

    @Test
    public void testBuscarEntregaPorId() {
        Entrega entrega = new Entrega();
        entrega.setIdEntrega(1L);

        when(entregaRepository.findById(1L)).thenReturn(Optional.of(entrega));

        Entrega resultado = (Entrega) entregaService.findByEntregaId(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getEntregaId());
    }
}
