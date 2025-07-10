package com.AgroTech.Seguimientos.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import com.AgroTech.Seguimientos.model.Seguimiento;
import com.AgroTech.Seguimientos.repository.SeguimientoRepository;

@ExtendWith(MockitoExtension.class)
public class SeguimientoServiceTest {

    @Mock
    private SeguimientoRepository repository;

    @InjectMocks
    private SeguimientoService service;

    @Test
    void testObtenerSeguimientos() {
        Seguimiento s1 = new Seguimiento(1L, "Bodega Central", new Date(), Seguimiento.EstadoSeguimiento.PENDIENTE);
        Seguimiento s2 = new Seguimiento(2L, "Sucursal Norte", new Date(), Seguimiento.EstadoSeguimiento.ENTREGADO);

        when(repository.findAll()).thenReturn(Arrays.asList(s1, s2));

        List<Seguimiento> resultado = service.findAll();

        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getEstado()).isEqualTo(Seguimiento.EstadoSeguimiento.EN_TRANSITO);
    }

    @Test
    void testGuardarSeguimiento() {
        Seguimiento nuevo = new Seguimiento(null, "Camino Rural", new Date(), Seguimiento.EstadoSeguimiento.PENDIENTE);
        Seguimiento guardado = new Seguimiento(10L, "Camino Rural", new Date(),Seguimiento.EstadoSeguimiento.ENTREGADO);

        when(repository.save(nuevo)).thenReturn(guardado);

        Seguimiento resultado = service.SaveSeguimiento(guardado);
        assertThat(resultado.getId()).isEqualTo(10L);
        assertThat(resultado.getUbicacionActual()).isEqualTo("Camino Rural");
    }
}