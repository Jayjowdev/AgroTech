package com.MicroTransportista.Transportista.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String direccionClientes;

    private boolean asignado;

    @ManyToOne
    //para que es @manyToOne?
    // esto le dice al JPA que cada entrega esa asociada a un transportista, eso seria de uno a muchos entregas

    private Transportista transportista;
    



}
