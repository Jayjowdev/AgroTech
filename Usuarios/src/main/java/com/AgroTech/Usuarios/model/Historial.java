package com.AgroTech.Usuarios.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Historial {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long historialId;

    private String ultimaCompra;
    private String ultimoArriendo;

    @Column(updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoLogin = new Date();
    
    private boolean activo = true;

    @OneToOne
    @JoinColumn(name = "usuarioId", referencedColumnName = "historialId")
    private Usuario usuario;
}
