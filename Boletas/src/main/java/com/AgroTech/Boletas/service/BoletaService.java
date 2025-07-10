package com.AgroTech.Boletas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AgroTech.Boletas.model.Boleta;
import com.AgroTech.Boletas.repository.BoletaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    //obtener todas la boletas
    public List<Boleta> findAll(){
        return boletaRepository.findAll();
    }

    //Obtener boleta por Id
    public Boleta getById(Long id){
        return boletaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Boleta no encontrada por Id"));
    }

    //Guardar nueva boleta
    public Boleta save(Boleta boleta){
        return boletaRepository.save(boleta);
    }

    //Actualizar boleta existente
    public Boleta updateBoleta(Long id, Boleta datos){
        Boleta existente = getById(id);
        existente.setCantidadProducto(datos.getCantidadProducto());
        existente.setPrecioUnitario(datos.getPrecioUnitario());
        existente.setFechaEmision(datos.getFechaEmision());
        existente.setTotal(datos.getCantidadProducto() * datos.getPrecioUnitario());
        return boletaRepository.save(existente);
    }
    //Metodo para elminar por id
    public void delete(Long id){
        if(!boletaRepository.existsById(id)){
            throw new RuntimeException("Boleta no encontrada por id:" + id);
        }
        boletaRepository.deleteById(id);
    }
}
