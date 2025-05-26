package com.AgroTech.Productos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.Productos.model.Producto;
import com.AgroTech.Productos.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoSevice {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(Long ProductoId) {
        return productoRepository.findById(ProductoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado (ID: " + ProductoId + ")"));
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }
    

}
