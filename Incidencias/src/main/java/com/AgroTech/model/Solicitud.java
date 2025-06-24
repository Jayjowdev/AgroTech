package com.AgroTech.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "solicitudes")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Solicitud {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long solicitudId;

    @Column(nullable= false, unique= true)
    private String titulo;

    @Column(nullable= false, length= 30)
    private String serialMaquinaria;

    @Column
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column
    private LocalDateTime fechaActualizacion;

    @Enumerated(EnumType.STRING)
    private EstadoTicket estado = EstadoTicket.PENDIENTE;


    @Enumerated(EnumType.STRING)
    private prioridadTicket prioridad = prioridadTicket.MEDIA;

    public enum prioridadTicket {
        BAJA,
        MEDIA,
        ALTA,
        CRITICA
    }

    public enum EstadoTicket {
        PENDIENTE,
        EN_PROCESO,
        RESUELTO,
        CERRADO
    }

    
}
