package com.AgroTech.Usuarios.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Historial {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long historialId;

    private String ultimaCompra;
    private String ultimoArriendo;
    private Date fechaCreacion = new Date();
    private Date ultimo_login = new Date();
    private boolean activo = true;
}
