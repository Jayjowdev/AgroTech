package com.MicroTransportista.Transportista.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MicroTransportista.Transportista.Model.Transportista;

public interface TransportistaRepository extends JpaRepository<Transportista, Long> {
    List<Transportista> findByDisponible(String nombre);
    // Este repositorio se encarga de la persistencia de datos de la entidad Transportista
    // No es necesario agregar métodos adicionales, ya que JpaRepository proporciona métodos CRUD por defecto

}
