package com.example.AgroTech.Repository;

import com.example.AgroTech.Model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AgroTechRepository {


    private List<Producto> listaProducto = new ArrayList<>();


    public List<Producto> obtenerProducto(){
        return listaProducto;

    }

    public Producto buscarId(int id){
        for (Producto producto : listaProducto){
            if (producto.getId() == id){
                return producto;
            }
        }
        return null;
    }

    public Producto guardar(Producto lib){
        listaProducto.add(lib);
        return lib;
    }

    public Producto actualizar(Producto lib){
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaProducto.size(); i++){
            if (listaProducto.get(i).getId() == lib.getId()) {
                id = lib.getId();
                idPosicion = i;
            }   
        }

        Producto producto1 = new Producto();
        producto1.setId(id);
        producto1.setNombre(lib.getNombre());
        producto1.setDescripcion(lib.getDescripcion());
        producto1.setValor(lib.getValor());

        listaProducto.set(idPosicion, producto1);
        return producto1;
    }

    public void eliminar(int id){
        Producto producto = buscarId(id);
        if (producto != null){
            listaProducto.remove(producto);
        }
    }
}
