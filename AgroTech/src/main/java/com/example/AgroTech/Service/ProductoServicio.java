package com.example.AgroTech.Service;

import org.springframework.stereotype.Service;
import com.example.AgroTech.Model.Producto;
import com.example.AgroTech.Repository.AgroTechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;



@Service
public class ProductoServicio {
    @Autowired
    private AgroTechRepository agroTechRepository;

    public List<Producto> geProductos(){
        return agroTechRepository.obtenerProducto();
    }

    public Producto saveProducto(Producto producto){
        return agroTechRepository.guardar(producto);
    }

    public Producto geProducto(int id) {
        return agroTechRepository.buscarId(id);

    }

    public Producto updateProducto(Producto producto) {
        return agroTechRepository.actualizar(producto);
    }

    public String deleteProducto(int id) {
        agroTechRepository.eliminar(id);
        return "Producto eliminado";
    }
}
