package com.AgroTech.Boletas.config;

import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.AgroTech.Boletas.model.Boleta;
import com.AgroTech.Boletas.repository.BoletaRepository;

@Configuration
public class LoadBoletaDatabase {

    @Bean
    CommandLineRunner initDatabase(BoletaRepository repo){
    return args -> {
            if (repo.count() == 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                repo.save(new Boleta(null, 10, 2500.0, 10 * 2500.0, sdf.parse("2025-07-10")));
                repo.save(new Boleta(null, 5, 1800.5, 5 * 1800.5, sdf.parse("2025-07-12")));
                repo.save(new Boleta(null, 3, 990.0, 3 * 990.0, sdf.parse("2025-07-14")));

                System.out.println("Boletas precargadas.");
            } else {
                System.out.println("Ya existen boletas. No se cargan nuevas.");
            }
        };
    }
}