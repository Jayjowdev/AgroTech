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

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(Long ProductoId) {
        return productoRepository.findById(ProductoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado (ID: " + ProductoId + ")"));
    }

    public Producto guardar(Producto producto) {
    return productoRepository.save(producto);
    }
    
    public void delete(Long productoId) {
        if (!productoRepository.existsById(productoId)) {
            throw new RuntimeException("Producto no encontrado (ID: " + productoId + ")");
        }
        productoRepository.deleteById(productoId);
    }

	public Object obtenerProductoPorId(long l) {
		//Metodo que utliza para el mockup y testeo
		throw new UnsupportedOperationException("Metodo sin implementar 'obtenerProductoPorId'");
	}
}
