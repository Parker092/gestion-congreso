package com.example.gestion_congreso.controller;

import com.example.gestion_congreso.model.Congresista;
import com.example.gestion_congreso.service.CongresistaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/congresistas")
public class CongresistaController {
    private final CongresistaService congresistaService;

    public CongresistaController(CongresistaService congresistaService) {
        this.congresistaService = congresistaService;
    }

    @GetMapping
    public List<Congresista> obtenerTodos() {
        return congresistaService.obtenerTodos();
    }

    @PostMapping
    public Congresista registrar(@RequestBody Congresista congresista) {
        return congresistaService.guardar(congresista);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        congresistaService.eliminar(id);
    }
}
