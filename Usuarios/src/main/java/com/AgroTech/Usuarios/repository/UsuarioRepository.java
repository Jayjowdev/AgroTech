package com.AgroTech.Usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AgroTech.Usuarios.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Object> {
    boolean existsByCorreo(String correo);
}
