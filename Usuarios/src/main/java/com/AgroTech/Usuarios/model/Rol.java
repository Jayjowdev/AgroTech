package com.AgroTech.Usuarios.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity//se reconozca con una entidad JPA
@Table(name= "roles")
@Data//metodos setters, getters
@NoArgsConstructor//constructor con todos los datos
@AllArgsConstructor//constructor vacio
@Builder
public class Rol {
    @Id//identificador primario
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Enumerated(EnumType.STRING)
    private NombreRol nombreRol;
    public enum NombreRol{
        ADMIN,
        CLIENTE,
        USUARIO
    }

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    //identificamos a relacion inversa: un rol puede ser asigando a muchos usuarios
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    @JsonIgnore //no se incluiran los usuarios cuando se consulte los roles
    private List<Usuario> users;
    
}
