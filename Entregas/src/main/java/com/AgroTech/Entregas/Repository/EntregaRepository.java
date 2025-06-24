package com.AgroTech.Entregas.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AgroTech.Entregas.Model.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository <Entrega, Long> {
    Optional<Entrega> findById(Long id);
    Optional<Entrega> buscarEntregaPorId(long id);
}
