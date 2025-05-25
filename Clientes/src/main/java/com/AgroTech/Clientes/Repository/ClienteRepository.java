package com.AgroTech.Clientes.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AgroTech.Clientes.Model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
