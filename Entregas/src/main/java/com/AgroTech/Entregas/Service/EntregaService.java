package com.AgroTech.Entregas.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.Entregas.Model.Entrega;
import com.AgroTech.Entregas.Repository.EntregaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    public List<Entrega> findAll() {
        return entregaRepository.findAll();
    }

    public Entrega findByEntregaId(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada (ID: " + entregaId + ")"));
    }
    
    public Entrega save(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

}
