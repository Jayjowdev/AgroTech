package com.AgroTech.Boletas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AgroTech.Boletas.model.Boleta;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Long> {

}
