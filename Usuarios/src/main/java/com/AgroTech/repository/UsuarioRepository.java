package com.AgroTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AgroTech.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Object> {
    Usuario findByUsername(String username);
    boolean existsByCorreo(String correo);
}
