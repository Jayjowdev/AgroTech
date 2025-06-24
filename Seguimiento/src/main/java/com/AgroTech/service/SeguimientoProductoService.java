package com.AgroTech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.model.SeguimientoProducto;
import com.AgroTech.repository.SeguimientoProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SeguimientoProductoService {

    @Autowired
    private SeguimientoProductoRepository seguimientoProductoRepository;

    public List<SeguimientoProducto> getAllSeguimientoProductos() {
        return seguimientoProductoRepository.findAll();
    }

    public SeguimientoProducto findById(Long id) {
        return seguimientoProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SeguimientoProducto not found with id: " + id));
    }

    public SeguimientoProducto findByCodigo(String codigo){
        return seguimientoProductoRepository.findByCodigoSeguimiento(codigo)
                .orElseThrow(() -> new RuntimeException("SeguimientoProducto not found with codigo: " + codigo));
    }

    public SeguimientoProducto  save(SeguimientoProducto seguimientoProducto){
        if (seguimientoProducto.getCodigoSeguimiento() == null || seguimientoProducto.getCodigoSeguimiento().isEmpty()) {
            throw new IllegalArgumentException("El código de seguimiento no puede estar vacío");
        }
        return seguimientoProductoRepository.save(seguimientoProducto);
    }

    public SeguimientoProducto update(Long id, SeguimientoProducto seguimientoActualizado){
        SeguimientoProducto seguimientoExistente = seguimientoProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SeguimientoProducto not found with id: " + id));

        seguimientoExistente.setUbicacionActual(seguimientoActualizado.getUbicacionActual());
        seguimientoExistente.setEstado(seguimientoActualizado.getEstado());
        seguimientoExistente.setFechaEntregaEstimada(seguimientoActualizado.getFechaEntregaEstimada());
        
        return seguimientoProductoRepository.save(seguimientoExistente);
    }

    public void delete(Long id){
        if(!seguimientoProductoRepository.existsById(id)) {
            throw new RuntimeException("SeguimientoProducto not found with id: " + id);
        }
        seguimientoProductoRepository.deleteById(id);
    }
    
}
