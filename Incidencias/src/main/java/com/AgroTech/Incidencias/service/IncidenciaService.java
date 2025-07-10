package com.AgroTech.Incidencias.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.Incidencias.model.Incidencia;
import com.AgroTech.Incidencias.repository.IncidenciaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class IncidenciaService {

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    //obtener todas las incidencias
    public List<Incidencia> findAll(){
        return incidenciaRepository.findAll();
    }

    //obtener incidencia por Id
    public Incidencia getById(Long id){
        return incidenciaRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Incidencia no encontrada por Id"));
    }

    //Guardar nueva incidencia
    public Incidencia saveIncidencia(Incidencia incidencia){
        return incidenciaRepository.save(incidencia);
     }

    //Actualizar incidencia existente
    public Incidencia updateIncidencia(Long id, Incidencia updated) {
        Incidencia incidencia = getById(id);
        incidencia.setSerialMaquina(updated.getSerialMaquina());
        incidencia.setFechaEstimada(updated.getFechaEstimada());
        incidencia.setEstadoIncidencia(updated.getEstadoIncidencia());
        incidencia.setEstadoMaquina(updated.getEstadoMaquina());
        return incidenciaRepository.save(incidencia);
    }

    //Eliminar una incidencia por Id
    public void delete(Long id){
        if(!incidenciaRepository.existsById(id)){
            throw new RuntimeException("Incidencia por id no encontrado: "+id);
        }
        incidenciaRepository.deleteById(id);
    }
}