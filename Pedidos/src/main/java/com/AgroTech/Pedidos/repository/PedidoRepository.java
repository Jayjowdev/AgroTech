package com.AgroTech.Pedidos.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.AgroTech.Pedidos.model.Pedidos;

@Repository
public interface PedidoRepository extends ReactiveCrudRepository<Pedidos, Integer> {

}
