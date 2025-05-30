package com.AgroTech.Usuarios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long usuarioId;
    private String nombre;
    private String correo;
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;
    public enum Rol {
        ADMIN,
        USUARIO,
        TECNICO
    } 
}
