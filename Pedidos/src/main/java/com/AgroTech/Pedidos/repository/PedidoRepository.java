package com.AgroTech.Pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AgroTech.Pedidos.model.Pedidos;

@Repository
public interface PedidoRepository extends JpaRepository<Pedidos, Long> {

}
