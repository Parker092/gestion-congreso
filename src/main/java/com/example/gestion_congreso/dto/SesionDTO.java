package com.example.gestion_congreso.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SesionDTO {
    private String sala;
    private LocalDateTime fechaHora;
    private Long chairmanId;
}
