package com.AgroTech.Usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.Usuarios.model.Rol;
import com.AgroTech.Usuarios.repository.RolRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    //metodos para obtener todos los roles
    public List<Rol> obtenerRols(){
        return rolRepository.findAll();
    }
    //metodos para obtener un rol mediante su id
    public Rol obtenerRolPorId(Long id){
        return rolRepository.findById(id).orElseThrow(()-> new RuntimeException("Rol no encontrado id:"+ id));
    }
}
