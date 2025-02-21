package com.example.gestion_congreso.controller;

import com.example.gestion_congreso.model.Sesion;
import com.example.gestion_congreso.service.SesionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sesiones")
public class SesionController {
    private final SesionService sesionService;

    public SesionController(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    @GetMapping
    public List<Sesion> listarSesiones() {
        return sesionService.obtenerTodas();
    }

    @PostMapping
    public Sesion programarSesion(@RequestBody Sesion sesion) {
        return sesionService.guardar(sesion);
    }
}
