package com.AgroTech.Productos.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.AgroTech.Productos.model.Producto;
import com.AgroTech.Productos.repository.ProductoRepository;

@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(ProductoRepository productoRepo){
        return args ->{
            //si no hay registros en las tablas se insertan las de precargadas
            if(productoRepo.count()==0){
                //cargar los productos
               Producto producto = new Producto();
               producto.setNombre("Producto");
               productoRepo.save(producto);

               //cargar los productos
               productoRepo.save(new Producto(null, "Tractor cocechador-John Deere", 11250000, 2));
               productoRepo.save(new Producto(null, "Tractor frutero-John Deere", 15000000, 2));
               System.out.println("Datos iniciales Cargados");
            }
            else{
                System.out.println("Datos ya existentes , no se cargaran nuevos datos");
            }
        };
    }
}
