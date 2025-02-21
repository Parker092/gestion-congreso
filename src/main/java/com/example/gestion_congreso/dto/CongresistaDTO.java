package com.example.gestion_congreso.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CongresistaDTO {
    private String nombre;
    private String apellido;
    private String institucion;
    private String correoElectronico;
    private String telefonoMovil;
}
