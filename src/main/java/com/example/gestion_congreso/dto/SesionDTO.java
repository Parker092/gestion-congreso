package com.example.gestion_congreso.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor  // ✅ Constructor con todos los argumentos
@NoArgsConstructor   // ✅ Constructor vacío necesario para serialización
public class SesionDTO {
    private Long id;
    private String sala;
    private LocalDateTime fechaHora;
    private Long chairmanId;
    private String chairmanNombre;
}
