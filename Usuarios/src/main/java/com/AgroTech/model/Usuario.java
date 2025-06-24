package com.AgroTech.model;

import java.util.Collection;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long usuarioId;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false, unique = true)
    private String telefono;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (
        name = "usuarioRol",
        joinColumns = @JoinColumn(name = "usuarioId"),
        inverseJoinColumns = @JoinColumn(name = "rolId")
    )
    private Collection<Rol> rol;

    @OneToMany(mappedBy = "usuario")
    private List<Historial> historial;
}
