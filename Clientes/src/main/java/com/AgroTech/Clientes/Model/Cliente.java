package com.AgroTech.Clientes.Model;

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
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable=false)
    private Long ClienteId;

    @Column(name = "nombre", nullable=false)
    private String nombre;

    @Column(name = "apellidos", nullable=false)
    private String apellidos;

    @Column(name = "telefono", nullable=false)
    private String telefono;

    @Column(name = "email", nullable=false)
    private String email;

    @Column(name = "direccion", nullable=false)
    private String direccion;

    @Column(name = "fecha registro", nullable=false)
    private Date fechaRegistro;


}
