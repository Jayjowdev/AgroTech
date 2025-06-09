package com.AgroTech.Usuarios.model;

import java.util.Date;

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
    private boolean activo = true;
    private Date fechaCreacion = new Date();
    private Date ultimo_login = new Date();


    @Enumerated(EnumType.STRING)
    private Rol rol;
    public enum Rol {
        ADMIN,
        USUARIO,
        TECNICO
    } 
}
