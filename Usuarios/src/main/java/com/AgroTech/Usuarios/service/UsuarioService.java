package com.AgroTech.Usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.AgroTech.Usuarios.model.Rol;
import com.AgroTech.Usuarios.model.Usuario;
import com.AgroTech.Usuarios.repository.RolRepository;
import com.AgroTech.Usuarios.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void guardarPassword(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }

    //metodo para buscar todos los Usuarios
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
    return usuarioRepository.save(usuario);
    }

    //metodo para buscar usuarios por id
    public Usuario getById(Long id){
        return usuarioRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
    }

    //metodo para guardar cliente
    public Usuario crearUsuario(String username, String password, Long roleId){
        //verificar si el Rol existe
        Rol rol = rolRepository.findById(roleId)
        .orElseThrow(()-> new RuntimeException("Rol ya tiene un id "+ roleId));

        //si puedo crear el usuario nuevo
        Usuario nuevo = new Usuario();
        nuevo.setUsername(username);
        nuevo.setPassword(password);
        nuevo.setRol(rol);
        return usuarioRepository.save(nuevo);
    }


    //metodo para actualizar usuario
    public Usuario update(Long id, Usuario usuarioActualizado) {
    Usuario existente = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

    // Actualizar los campos necesarios (ajusta según tu modelo)
    existente.setUsername(usuarioActualizado.getUsername());
    existente.setCorreo(usuarioActualizado.getCorreo());
    existente.setPassword(usuarioActualizado.getPassword());
    existente.setRol(usuarioActualizado.getRol());

    return usuarioRepository.save(existente);
}

    //metodo para elminar por id
    public void delete(Long id) {
    if (!usuarioRepository.existsById(id)) {
        throw new RuntimeException("Usuario no encontrado con ID: " + id);
    }
    usuarioRepository.deleteById(id);
}

}