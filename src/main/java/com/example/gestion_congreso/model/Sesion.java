package com.example.gestion_congreso.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Sesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sala;

    @Column(nullable = false)
    private LocalDateTime fechaHora;  // ✅ Se asegura que sea LocalDateTime en la base de datos

    @OneToMany(mappedBy = "sesion")
    @JsonManagedReference
    private List<Presentacion> presentaciones;

    @ManyToOne
    @JoinColumn(name = "chairman_id", nullable = false)
    private Congresista chairman;

    // ✅ Getters y Setters
    public Long getId() {
        return id;
    }

    public String getSala() {
        return sala;
    }

    public LocalDateTime getFechaHora() {  // ✅ Se mantiene LocalDateTime
        return fechaHora;
    }

    public List<Presentacion> getPresentaciones() {
        return presentaciones;
    }

    public Congresista getChairman() {
        return chairman;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setChairman(Congresista chairman) {
        this.chairman = chairman;
    }
}
