package com.AgroTech.Usuarios.model;

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
@Table(name= "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//autoincrementable
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String Correo;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String numeroTelefono;

}
