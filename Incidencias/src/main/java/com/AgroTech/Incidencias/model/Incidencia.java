package com.AgroTech.Incidencias.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "Incidencias")
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class Incidencia {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String serialMaquina;

    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaEstimada;

     @Enumerated(EnumType.STRING)
     @Column(nullable=false)
     private EstadoIncidencia estadoIncidencia = EstadoIncidencia.ABIERTA;

     @Enumerated(EnumType.STRING)
     @Column(nullable=false)
    private EstadoMaquina estadoMaquina = EstadoMaquina.OPERATIVA;

    public enum EstadoIncidencia {
        ABIERTA,
        EN_PROCESO,
        RESUELTA,
        CERRADA
    }

    public enum EstadoMaquina{
        OPERATIVA,
        MANTENIMIENTO,
        FUERA_SERVICIO,
        EN_ARRIENDO,
        EN_VENTA,
        VENDIDA
    }
}
