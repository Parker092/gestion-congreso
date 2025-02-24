package com.example.gestion_congreso.controller;

import com.example.gestion_congreso.model.Congresista;
import com.example.gestion_congreso.service.CongresistaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Congresistas", description = "Gestión de los asistentes al congreso")
@RestController
@RequestMapping("/api/congresistas")
public class CongresistaController {
    private final CongresistaService congresistaService;

    public CongresistaController(CongresistaService congresistaService) {
        this.congresistaService = congresistaService;
    }

    @Operation(summary = "Listar congresistas", description = "Devuelve la lista de todos los congresistas registrados. Accesible por usuarios con roles USER o ADMIN.")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<Congresista> obtenerTodos() {
        return congresistaService.obtenerTodos();
    }

    @Operation(summary = "Registrar un congresista", description = "Registra un nuevo congresista en el sistema. Solo accesible para ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Congresista> registrar(@RequestBody Congresista congresista) {
        Congresista nuevoCongresista = congresistaService.guardar(congresista);
        return ResponseEntity.ok(nuevoCongresista);
    }

    @Operation(summary = "Eliminar un congresista", description = "Elimina un congresista por su ID. Solo accesible para ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        congresistaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
