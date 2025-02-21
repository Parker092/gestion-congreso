package com.example.gestion_congreso.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Sesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sala;
    private String fechaHora;

    @OneToMany(mappedBy = "sesion")
    @JsonManagedReference  // 🔹 Permite serializar presentaciones sin generar bucles
    private List<Presentacion> presentaciones;

    @ManyToOne
    @JoinColumn(name = "chairman_id")
    private Congresista chairman;

    // ✅ Getters y Setters
    public Long getId() {
        return id;
    }

    public String getSala() {
        return sala;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public List<Presentacion> getPresentaciones() {
        return presentaciones;
    }

    public Congresista getChairman() {
        return chairman;
    }
}