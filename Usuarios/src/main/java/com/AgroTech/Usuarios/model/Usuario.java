package com.AgroTech.Usuarios.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable= false, unique= true, length = 50 )
    private String username;
    
    @Column(nullable=false)
    private String correo;

    @Column(nullable= false, length =50)
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id") // nombre de la columna foránea
    @JsonIgnoreProperties("users") // ignorar el atributo users cuando serialicemos el rol
    private Rol rol;

}
