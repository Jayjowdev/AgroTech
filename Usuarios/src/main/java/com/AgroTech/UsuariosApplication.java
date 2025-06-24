package com.AgroTech;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.AgroTech.model.Rol;
import com.AgroTech.repository.RolRepository;



@SpringBootApplication
public class UsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuariosApplication.class, args);
	}

	@Bean
	public CommandLineRunner initRol(RolRepository rolRepository){
		return args -> {
			if (rolRepository.findByNombreRol("USER") == null) {
				rolRepository.save(new Rol(null, "USER"));
			}
			if (rolRepository.findByNombreRol("ADMIN") == null) {
				rolRepository.save(new Rol(null, "ADMIN"));
			}
			if (rolRepository.findByNombreRol("COLABORADOR") == null) {
				rolRepository.save(new Rol(null, "COLABORADOR"));
			}
		};
	}
}
