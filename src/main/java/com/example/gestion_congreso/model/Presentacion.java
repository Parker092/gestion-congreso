package com.example.gestion_congreso.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Presentacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trabajo_id")
    private Trabajo trabajo;

    @ManyToOne
    @JoinColumn(name = "sesion_id")
    @JsonBackReference  // 🔹 Evita el bucle infinito al serializar sesiones
    private Sesion sesion;

    @ManyToOne
    @JoinColumn(name = "ponente_id")
    private Congresista ponente;

    // ✅ Getters y Setters
    public Long getId() {
        return id;
    }

    public Trabajo getTrabajo() {
        return trabajo;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public Congresista getPonente() {
        return ponente;
    }
}
