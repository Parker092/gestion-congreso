package com.example.gestion_congreso.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Congresista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String institucion;
    private String correoElectronico;
    private String telefonoMovil;

    @OneToMany(mappedBy = "congresista")
    private List<AutorTrabajo> trabajosComoAutor;

    // ✅ Asegurar que este método exista
    public Long getId() {
        return id;
    }
}
