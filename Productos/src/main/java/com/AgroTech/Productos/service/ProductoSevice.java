package com.AgroTech.Productos.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.Productos.model.Producto;
import com.AgroTech.Productos.repository.ProductoRepository;
import com.AgroTech.Productos.webclient.ProveedorClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoSevice {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedorClient proveedorClient;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(Long ProductoId) {
        return productoRepository.findById(ProductoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado (ID: " + ProductoId + ")"));
    }

    public Producto save(Producto producto) {
        // Validar que el proveedor existe
        Map<String, Object> proveedor = proveedorClient.getProveedorById(producto.getProveedorId());
        if (proveedor == null || proveedor.isEmpty()) {
            throw new RuntimeException("Proveedor no encontrado para el ID: " + producto.getProveedorId());
        }

        return productoRepository.save(producto);
    }
    
    public void delete(Long productoId) {
        if (!productoRepository.existsById(productoId)) {
            throw new RuntimeException("Producto no encontrado (ID: " + productoId + ")");
        }
        productoRepository.deleteById(productoId);
    }
}
