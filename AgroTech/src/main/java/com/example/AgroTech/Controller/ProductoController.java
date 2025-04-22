package com.example.AgroTech.Controller;

import com.example.AgroTech.Model.Producto;
import com.example.AgroTech.Service.ProductoServicio;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public List<Producto> listaProductos(){
        return productoServicio.geProductos();
    }

    @PostMapping
    public Producto agregarProducto(@RequestBody Producto producto) {
        return productoServicio.saveProducto(producto);
    }

    @PutMapping("{id}")
    public Producto actualizaProducto(@PathVariable int id, @RequestBody Producto producto){
        return productoServicio.updateProducto(producto);
    }

    @DeleteMapping("{id}")
    public String eliminarProducto(@PathVariable int id) {
        return productoServicio.deleteProducto(id);
    }
}
