package com.AgroTech.Productos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.Productos.model.Producto;
import com.AgroTech.Productos.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    //metodo para buscar todos los productos
    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    //metodo para buscar un producto por id
    public  Producto getProductoId(long id){
        return productoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("producto no encontrado por su id"));
    }

    //metodo para guardar un producto
    public Producto saveProducto(Producto producto){
        return productoRepository.save(producto);
    }

    // Actualizar un producto existente
    public Producto updateProducto(long id, Producto datosActualizados) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado por su ID"));

        producto.setNombre(datosActualizados.getNombre());
        producto.setPrecio(datosActualizados.getPrecio());
        producto.setStock(datosActualizados.getStock());

        return productoRepository.save(producto);
    }

    //eliminar un producto por id
    public void deleteProducto(Long id){
        if(!productoRepository.existsById(id)){
            throw new RuntimeException("Producto no encontrado por id: "+id);
        }
        productoRepository.deleteById(id);
    }
}
