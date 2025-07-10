package com.AgroTech.Usuarios.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.AgroTech.Usuarios.model.Rol;
import com.AgroTech.Usuarios.model.Usuario;
import com.AgroTech.Usuarios.repository.RolRepository;
import com.AgroTech.Usuarios.repository.UsuarioRepository;

@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(RolRepository rolRepo, UsuarioRepository usuarioRepo){
        return args ->{
            //si no hay registros en las tablas inserto los de defecto
            if(rolRepo.count()== 0 && usuarioRepo.count() == 0){
                //cargar los roles
                Rol admin = new Rol();
                admin.setNombre("Administrador");
                rolRepo.save(admin);

                Rol cliente = new Rol();
                cliente.setNombre("Cliente");
                rolRepo.save(cliente);

                //cargar los usuarios
                usuarioRepo.save(new Usuario(null, "Juan", "juan@email.com", "1234", admin));
                usuarioRepo.save(new Usuario(null, "Pedro", "pedro@email.com", "12345", cliente));
                System.out.println("Datos iniciales Cargados");
            }
            else{
                System.out.println("Datos ya existenm no se cargaron nuevos datos");
            }
        };
    }
}
