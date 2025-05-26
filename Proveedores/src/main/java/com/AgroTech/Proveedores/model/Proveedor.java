package com.AgroTech.Proveedores.model;

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
@Table(name = "proveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long ProveedorId;

    @Column (name = "nombre")
    private String Nombre;

    @Column (name= "contacto")
    private String contacto;

    @Column (name= "telefono")
    private String telefono;

    @Column (name= "direccion")
    private String direccion;
}
