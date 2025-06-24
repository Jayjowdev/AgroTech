package com.AgroTech.Productos.model;

import java.math.BigDecimal;

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
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Producto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long ProductoId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private BigDecimal precio;

    @Column(name = "stock")
    private int stock;

    @Column(name= "categoria")
    private String categoria;

    @Column(name= "estado")
    private String estado;


    //Metodos que se utilizan para la creacion de mockUp y testeo
    public void setId(long l) {
        throw new UnsupportedOperationException("metodo sin implementar'setId'");
    }
    public Long getId() {
        throw new UnsupportedOperationException("metodo sin implementar 'getId'");
    }
    public void setIdProducto(long l) {
        throw new UnsupportedOperationException("metodo sin implementar 'setIdProducto'");
    }
    public Long getIdProducto() {
        throw new UnsupportedOperationException("metodo sin implementar 'getIdProducto'");
    }

}
