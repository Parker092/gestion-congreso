package com.example.gestion_congreso.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class TrabajoDTO {
    private Long id;
    private String titulo;
    private String resumen;
    private List<Long> autoresIds;
}
