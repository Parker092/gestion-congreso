package com.example.gestion_congreso.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class AutorTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trabajo_id")
    @JsonBackReference
    private Trabajo trabajo;

    @ManyToOne
    @JoinColumn(name = "congresista_id")
    @JsonBackReference
    private Congresista congresista;

    // 🔹 Método necesario para acceder a Congresista en TrabajoController
    public Congresista getCongresista() {
        return congresista;
    }
}
