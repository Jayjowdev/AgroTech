package com.AgroTech.Seguimientos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.Seguimientos.model.Seguimiento;
import com.AgroTech.Seguimientos.repository.SeguimientoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SeguimientoService {

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    //obtener todos los seguimientos
    public List<Seguimiento> findAll(){
        return seguimientoRepository.findAll();
    }

    //obtener seguimiento por Id
    public Seguimiento getById(Long id ){
        return seguimientoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Seguimiento no encontrado por Id"));
    }

    //Guardar nuevo seguimiento
    public Seguimiento SaveSeguimiento(Seguimiento seguimiento){
        return seguimientoRepository.save(seguimiento);
    }

    //Actualizar seguimiento existente
    public Seguimiento UpdateSeguimiento(Long id,Seguimiento datos){
        Seguimiento existente = getById(id);
        existente.setUbicacionActual(datos.getUbicacionActual());
        existente.setFechaEntrega(datos.getFechaEntrega());
        existente.setEstado(datos.getEstado());
        return seguimientoRepository.save(existente);
    }

    public void deleteSeguimiento(Long id){
        if(!seguimientoRepository.existsById(id)){
            throw new RuntimeException("Seguimiento no encontrado por id: "+ id);
        }
        seguimientoRepository.deleteById(id);
    }

}
