package com.example.gestion_congreso.controller;

import com.example.gestion_congreso.model.Presentacion;
import com.example.gestion_congreso.service.PresentacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presentaciones")
public class PresentacionController {
    private final PresentacionService presentacionService;

    public PresentacionController(PresentacionService presentacionService) {
        this.presentacionService = presentacionService;
    }

    @GetMapping
    public List<Presentacion> listarPresentaciones() {
        return presentacionService.obtenerTodas();
    }

    @PostMapping
    public Presentacion asignarPresentacion(@RequestBody Presentacion presentacion) {
        return presentacionService.guardar(presentacion);
    }
}
