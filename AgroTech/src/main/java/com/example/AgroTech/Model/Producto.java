package com.example.AgroTech.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Producto {


    private int id;
    private String Nombre;
    private String Descripcion;
    private int Valor;
}
