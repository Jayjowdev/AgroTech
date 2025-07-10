package com.AgroTech.Usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AgroTech.Usuarios.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

}
