package com.example.gestion_congreso.controller;

import com.example.gestion_congreso.model.Presentacion;
import com.example.gestion_congreso.service.PresentacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Presentaciones", description = "Gestión de presentaciones en el congreso")
@RestController
@RequestMapping("/api/presentaciones")
public class PresentacionController {
    private final PresentacionService presentacionService;

    public PresentacionController(PresentacionService presentacionService) {
        this.presentacionService = presentacionService;
    }

    @Operation(summary = "Listar presentaciones", description = "Devuelve la lista de todas las presentaciones programadas. Accesible para usuarios con roles USER o ADMIN.")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<Presentacion>> listarPresentaciones() {
        return ResponseEntity.ok(presentacionService.obtenerTodas());
    }

    @Operation(summary = "Asignar una presentación", description = "Asigna una nueva presentación a una sesión. Solo accesible para ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Presentacion> asignarPresentacion(@RequestBody Presentacion presentacion) {
        return ResponseEntity.ok(presentacionService.guardar(presentacion));
    }
}
