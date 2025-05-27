package com.AgroTech.Usuarios.dto;

import com.AgroTech.Usuarios.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String correo;
    private Usuario.Rol rol;
}
