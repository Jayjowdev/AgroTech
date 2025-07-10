package com.AgroTech.Seguimientos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AgroTech.Seguimientos.model.Seguimiento;

@Repository
public interface SeguimientoRepository extends  JpaRepository<Seguimiento, Long> {

}
