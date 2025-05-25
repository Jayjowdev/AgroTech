package com.AgroTech.Pedidos.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Pedidos {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_pedido", nullable=false)
    private Integer idPedido;

    @Column(name = "id_cliente", nullable=false)
    private Long clienteId;

    @Column(name = "Fecha_pedido")
    private LocalDate fechaPedido;

    @Column(name= "estado")
    private String estado;

    @Column(name="total")
    private BigDecimal total;
}
