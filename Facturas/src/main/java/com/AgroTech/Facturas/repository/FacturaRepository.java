package com.AgroTech.Facturas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AgroTech.Facturas.model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
