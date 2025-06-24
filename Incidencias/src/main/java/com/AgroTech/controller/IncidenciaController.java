package com.AgroTech.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AgroTech.model.Incidencia;
import com.AgroTech.service.IncidenciaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/incidencias")
@RequiredArgsConstructor
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

   @PostMapping
    public Incidencia crearIncidencia(
            @RequestBody Incidencia incidencia,
            @RequestParam String solicitudId) {
        Long solicitudIdLong = Long.parseLong(solicitudId);
        return incidenciaService.crearIncidencia(incidencia, solicitudIdLong);
    }

    @GetMapping("/incidencia")
    public List<Incidencia> obtenerTodasIncidencias() {
        return incidenciaService.obtenerTodasIncidencias();
    }

    @GetMapping("/{id}")
    public Incidencia obtenerIncidenciaPorId(@PathVariable Long id) {
        return incidenciaService.obtenerIncidenciaPorId(id);
    }

    @PutMapping("/{id}/estado")
    public Incidencia actualizarEstadoIncidencia(
            @PathVariable Long id,
            @RequestParam Incidencia.EstadoIncidencia estado) {
        return incidenciaService.actualizarEstadoIncidencia(id, estado);
    }

    @PutMapping("/{id}/estado-maquinaria")
    public Incidencia actualizarEstadoMaquinaria(
            @PathVariable Long id,
            @RequestParam Incidencia.EstadoMaquinaria estado) {
        return incidenciaService.actualizarEstadoMaquinaria(id, estado);
    }

}