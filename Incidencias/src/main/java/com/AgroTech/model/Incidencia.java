package com.AgroTech.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "incidencias")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Incidencia {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long incidenciaId;

    @Column(nullable=false)
    private Long maquinariaId;

    @Column(nullable=false)
    private String titulo;

    @Column(nullable=false, length=1000)
    private String descripcion;

    @Column(nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReporte = new Date();

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private EstadoIncidencia estado = EstadoIncidencia.ABIERTA;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private EstadoMaquinaria estadoMaquinaria = EstadoMaquinaria.OPERATIVA;
    
    @ManyToOne
    @JoinColumn(name = "solicitudId", nullable = false)
    private Solicitud solicitud;

    public enum EstadoIncidencia {
        ABIERTA,
        EN_PROCESO,
        RESUELTA,
        CERRADA
    }

    public enum EstadoMaquinaria {
        OPERATIVA,
        MANTENIMIENTO,
        FUERA_DE_SERVICIO
    }

    public Long getId() {
        throw new UnsupportedOperationException("Metodo sin implementar'getId'");
        
    } //Metodos que se utilizaran para los testeo unitarios

    public void setId(long l) {
        throw new UnsupportedOperationException("Metodo sin implementar 'setId'");
    }

    

}






