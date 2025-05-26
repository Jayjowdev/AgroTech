package com.AgroTech.DetallesPedidos.model;

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
@Table(name = "detalle_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DetallePedido {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long detallePedidoId;

    @Column (name = "cantidad_producto")
    private int cantidadProducto;

    @Column (name = "precio_unitario")
    private double precioUnitario;
 
}
