package com.AgroTech.Entregas.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.Entregas.model.Entrega;
import com.AgroTech.Entregas.repository.EntregaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EntregaService {
@Autowired
    private EntregaRepository entregaRepository;

    // Mostrar todas las entregas
    public List<Entrega> obtenerEntregas() {
        return entregaRepository.findAll();
    }

    // Buscar entrega por ID
    public Entrega getEntregaId(Long id) {
        return entregaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Entrega no encontrada por ese id"));
    }

    // Crear nueva entrega
    public Entrega crear(String estado, Date fechaEstimada) {
        Entrega nuevo = new Entrega();
        nuevo.setEstado(estado);
        nuevo.setFechaEstimada(fechaEstimada);
        return entregaRepository.save(nuevo);
    }

    // Actualizar entrega existente
    public Entrega actualizarEntrega(Long id, String estado, Date fechaEstimada) {
        Entrega existente = entregaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Entrega no encontrada por Id: " + id));

        existente.setEstado(estado);
        existente.setFechaEstimada(fechaEstimada);
        return entregaRepository.save(existente);
    }

    //Elminar una entrega existente
    public void delete(Long id){
        if(!entregaRepository.existsById(id)){
            throw new RuntimeException("Entrega no encontrada por id" + id); 
        }
        entregaRepository.deleteById(id);
    }
}