package com.AgroTech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AgroTech.model.Incidencia;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    List<Incidencia> findByMaquinariaId(Long maquinariaId);
}
