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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seguimientoProductos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class SeguimientoProducto {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long productoId;

    @Column(name = "codigo_seguimiento",nullable = false, unique = true)
    private String codigoSeguimiento;

    @Column(name = "ubicacionActual",nullable = false)
    private String ubicacionActual;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoSeguimiento estado;

    @Column(name = "fechaRegistro",nullable = false)
    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaEntregaEstimada;

    public enum EstadoSeguimiento {
        PENDIENTE,
        EN_TRANSITO,
        ENTREGADO,
        CANCELADO
    }
}

