package com.AgroTech.Facturas.model;

import java.math.BigDecimal;
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
@Table(name = "facturas")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Factura {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_factura", nullable=false)
    private long FacturaId;

    @Column(name= "Fecha_emision")
    private Date fechaEmision;

    @Column(name= "Monto_total")
    private BigDecimal montoTotal;

    @Column(name= "Estado_pago")
    private String estadoPago;

    @Column(name= "id_pedido")
    private String idPedido;

}
