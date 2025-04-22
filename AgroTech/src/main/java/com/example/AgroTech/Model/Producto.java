package com.example.AgroTech.Model;

import lombok.AllArgsConstructor;
import lombol.Data;
import lombok.NoArgContructor;

@Data
@AllArgsConstructor
@NoArgContructor

public class Producto {


    private int id;
    private String Nombre;
    private String Descripcion;
    private int Valor;
}
