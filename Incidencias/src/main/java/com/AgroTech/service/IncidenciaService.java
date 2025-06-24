package com.AgroTech.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.AgroTech.model.Incidencia;
import com.AgroTech.model.Solicitud;
import com.AgroTech.repository.IncidenciaRepository;
import com.AgroTech.repository.SolicitudRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncidenciaService {
    
    private final IncidenciaRepository incidenciaRepository;
    private final SolicitudRepository solicitudRepository;

    
public Incidencia crearIncidencia(Incidencia incidencia, Long solicitudId) {
        Solicitud solicitud = solicitudRepository.findById(solicitudId)
            .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con ID: " + solicitudId));
        if (solicitud == null) {
            throw new RuntimeException("Maquinaria no encontrada");
        }
        incidencia.setSolicitud(solicitud);
        return incidenciaRepository.save(incidencia);
    }

    public List<Incidencia> obtenerTodasIncidencias() {
        return incidenciaRepository.findAll();
    }

    public Incidencia actualizarEstadoIncidencia(Long id, Incidencia.EstadoIncidencia nuevoEstado) {
        Incidencia incidencia = incidenciaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));
        
        incidencia.setEstado(nuevoEstado);
        return incidenciaRepository.save(incidencia);
    }

    public Incidencia actualizarEstadoMaquinaria(Long id, Incidencia.EstadoMaquinaria nuevoEstado) {
        Incidencia incidencia = incidenciaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));
        
        incidencia.setEstadoMaquinaria(nuevoEstado);
        return incidenciaRepository.save(incidencia);
    }

    public Incidencia obtenerIncidenciaPorId(Long incidenciaId) {
        return incidenciaRepository.findById(incidenciaId)
            .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con ID: " + incidenciaId));
    }
    
    
}