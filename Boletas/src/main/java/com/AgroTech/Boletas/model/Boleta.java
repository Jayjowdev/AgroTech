package com.AgroTech.Boletas.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name= "Boleta")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Boleta {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column (name= "CantidadProducto",nullable=false)
    private int CantidadProducto;

    @Column (name= "PrecioUnitario",nullable=false)
    private double PrecioUnitario;

    @Column(name = "Total", nullable = false)
    private double total;
    
    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaEmision;
}
