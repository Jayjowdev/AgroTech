package com.AgroTech.Entregas.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "entregas")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Entrega {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long EntregaId;

    @Column(name = "Fecha_estimada")
    private Date FechaEstimada;

    @Column(name= "estado")
    private String estado;

    @Column(name= "pedido_id")
    private String idPedido;
}

