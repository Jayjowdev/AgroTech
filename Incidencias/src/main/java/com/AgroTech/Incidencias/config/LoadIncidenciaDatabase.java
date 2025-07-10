package com.AgroTech.Incidencias.config;

import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.AgroTech.Incidencias.model.Incidencia;
import com.AgroTech.Incidencias.model.Incidencia.EstadoIncidencia;
import com.AgroTech.Incidencias.model.Incidencia.EstadoMaquina;
import com.AgroTech.Incidencias.repository.IncidenciaRepository;

@Configuration
public class LoadIncidenciaDatabase {
    @Bean
    CommandLineRunner initDatabase(IncidenciaRepository repo){
        return args ->{
            if(repo.count()==0){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                repo.save(new Incidencia(null, "JD12345", sdf.parse("2025-07-10"),
                        EstadoIncidencia.RESUELTA, EstadoMaquina.OPERATIVA));

                 repo.save(new Incidencia(null, "JD67890", sdf.parse("2025-07-12"),
                        EstadoIncidencia.EN_PROCESO, EstadoMaquina.MANTENIMIENTO));

                repo.save(new Incidencia(null, "JD54321", sdf.parse("2025-07-15"),
                        EstadoIncidencia.ABIERTA, EstadoMaquina.FUERA_SERVICIO));

                System.out.println("Incidencias precargadas.");
            } else {
                System.out.println("Ya existen incidencias, no se cargan nuevas.");
            }
        };
    }
}