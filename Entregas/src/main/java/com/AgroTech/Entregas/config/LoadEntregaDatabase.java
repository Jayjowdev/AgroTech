package com.AgroTech.Entregas.config;

import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.AgroTech.Entregas.model.Entrega;
import com.AgroTech.Entregas.repository.EntregaRepository;

@Configuration
public class LoadEntregaDatabase {
    @Bean
    CommandLineRunner initEntregaDatabase(EntregaRepository entregaRepository) {
        return args -> {
            if (entregaRepository.count() == 0) {
                // Formato para la fecha
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                entregaRepository.save(new Entrega(null,"Rancagua, Gran Av.4131","pendiente", sdf.parse("2025-07-20")));
                entregaRepository.save(new Entrega(null,"Santiago, Alameda 1231" ,"en camino", sdf.parse("2025-07-22")));
                entregaRepository.save(new Entrega(null,"Concepcion, Maipu 7542 ","entregada", sdf.parse("2025-07-01")));

                System.out.println("Datos de entregas precargadas en la base de datos.");
            } else {
                System.out.println("Entregas ya existen, no se cargan datos nuevos.");
            }
        };
    }
}
