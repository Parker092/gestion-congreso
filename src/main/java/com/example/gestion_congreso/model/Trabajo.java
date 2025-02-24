package com.example.gestion_congreso.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class Trabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String resumen;

    @OneToMany(mappedBy = "trabajo")
    private List<AutorTrabajo> autores;
}
