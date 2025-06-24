package com.AgroTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AgroTech.model.Rol;

@Repository
public interface RolRepository extends  JpaRepository<Rol, Object> {
    Rol findByNombreRol(String nombreRol);
}
