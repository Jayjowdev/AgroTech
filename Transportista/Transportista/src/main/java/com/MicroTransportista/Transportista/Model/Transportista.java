package com.MicroTransportista.Transportista.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Transportista {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private boolean disponible;

    private String ubicacion;

}
