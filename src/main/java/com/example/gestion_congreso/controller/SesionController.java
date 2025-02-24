package com.example.gestion_congreso.controller;

import com.example.gestion_congreso.dto.SesionDTO;
import com.example.gestion_congreso.service.SesionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sesiones")
@Tag(name = "Sesiones", description = "Gestión de sesiones del congreso") // ✅ Agregamos una etiqueta para agrupar en Swagger
public class SesionController {
    private final SesionService sesionService;

    public SesionController(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    @Operation(summary = "Listar todas las sesiones", description = "Devuelve una lista de sesiones con información del moderador (chairman).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de sesiones obtenida correctamente"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado si el usuario no está autenticado")
    })
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public List<SesionDTO> listarSesiones() {
        return sesionService.obtenerTodas();
    }

    @Operation(summary = "Programar una nueva sesión", description = "Permite a un administrador programar una sesión indicando la sala, fecha/hora y el moderador (chairman).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sesión creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en los datos enviados"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado si el usuario no tiene rol ADMIN")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SesionDTO> programarSesion(@RequestBody SesionDTO sesionDTO) {
        SesionDTO nuevaSesion = new SesionDTO(
                null, // ✅ ID se generará automáticamente en la BD
                sesionDTO.getSala(),
                sesionDTO.getFechaHora(),
                sesionDTO.getChairmanId(),
                sesionDTO.getChairmanNombre()
        );
        return ResponseEntity.ok(sesionService.guardar(nuevaSesion));
    }


    @Operation(summary = "Eliminar una sesión", description = "Permite a un administrador eliminar una sesión por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sesión eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Sesión no encontrada"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado si el usuario no tiene rol ADMIN")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSesion(@PathVariable Long id) {
        sesionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
