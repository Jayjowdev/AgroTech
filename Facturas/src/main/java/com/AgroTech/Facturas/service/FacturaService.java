package com.AgroTech.Facturas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.Facturas.model.Factura;
import com.AgroTech.Facturas.repository.FacturaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public List<Factura> findAll(){
        return facturaRepository.findAll();
    }

    public Factura findByFacturaId(Long FacturaId){
        return facturaRepository.findById(FacturaId)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada (ID: " + FacturaId + ")"));
    }

    public Factura save(Factura factura){
        return facturaRepository.save(factura);
    }
}
