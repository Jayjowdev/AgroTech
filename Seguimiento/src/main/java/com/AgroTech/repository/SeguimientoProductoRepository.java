package com.AgroTech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AgroTech.model.SeguimientoProducto;

@Repository
public interface SeguimientoProductoRepository extends JpaRepository<SeguimientoProducto, Object> {
   
    Optional<SeguimientoProducto> findByCodigoSeguimiento(String codigoSeguimiento);
}
