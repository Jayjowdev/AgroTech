package com.AgroTech.Seguimientos.config;

import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.AgroTech.Seguimientos.model.Seguimiento;
import com.AgroTech.Seguimientos.repository.SeguimientoRepository;

@Configuration
public class LoadSeguimientoDatabase {
    @Bean
    CommandLineRunner initDatabase (SeguimientoRepository repo){
    return args -> {
            if (repo.count() == 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                repo.save(new Seguimiento(null, "Bodega Central", sdf.parse("2025-07-10"), Seguimiento.EstadoSeguimiento.PENDIENTE));
                repo.save(new Seguimiento(null, "En Ruta a Rancagua", sdf.parse("2025-07-12"), Seguimiento.EstadoSeguimiento.EN_TRANSITO));
                repo.save(new Seguimiento(null, "Cliente Final", sdf.parse("2025-07-15"), Seguimiento.EstadoSeguimiento.ENTREGADO));

                System.out.println("Seguimientos precargados.");
            } else {
                System.out.println("Ya existen seguimientos. No se cargan nuevos.");
            }
        };
    }
}