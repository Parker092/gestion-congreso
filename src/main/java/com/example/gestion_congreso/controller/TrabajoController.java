package com.example.gestion_congreso.controller;

import com.example.gestion_congreso.dto.TrabajoDTO;
import com.example.gestion_congreso.model.Trabajo;
import com.example.gestion_congreso.service.TrabajoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.gestion_congreso.model.Congresista;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Trabajos", description = "Gestión de los trabajos científicos presentados en el congreso")
@RestController
@RequestMapping("/api/trabajos")
public class TrabajoController {
    private final TrabajoService trabajoService;

    public TrabajoController(TrabajoService trabajoService) {
        this.trabajoService = trabajoService;
    }

    @Operation(summary = "Listar trabajos", description = "Devuelve la lista de todos los trabajos registrados. Accesible para usuarios con roles USER o ADMIN.")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<TrabajoDTO>> listarTrabajos() {
        List<TrabajoDTO> trabajos = trabajoService.obtenerTodos()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(trabajos);
    }

    @Operation(summary = "Registrar un trabajo", description = "Registra un nuevo trabajo en el sistema. Solo accesible para ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TrabajoDTO> registrarTrabajo(@RequestBody TrabajoDTO trabajoDTO) {
        Trabajo trabajo = trabajoService.guardar(convertirAEntidad(trabajoDTO));
        return ResponseEntity.ok(convertirADTO(trabajo));
    }

    @Operation(summary = "Obtener un trabajo por ID", description = "Obtiene un trabajo específico basado en su ID. Accesible para usuarios con roles USER o ADMIN.")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<TrabajoDTO> obtenerTrabajo(@PathVariable Long id) {
        Trabajo trabajo = trabajoService.obtenerPorId(id);
        return ResponseEntity.ok(convertirADTO(trabajo));
    }

    @Operation(summary = "Actualizar un trabajo", description = "Actualiza la información de un trabajo existente. Solo accesible para ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TrabajoDTO> actualizarTrabajo(@PathVariable Long id, @RequestBody TrabajoDTO trabajoDTO) {
        Trabajo trabajoActualizado = trabajoService.actualizarTrabajo(id, convertirAEntidad(trabajoDTO));
        return ResponseEntity.ok(convertirADTO(trabajoActualizado));
    }

    @Operation(summary = "Eliminar un trabajo", description = "Elimina un trabajo específico basado en su ID. Solo accesible para ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTrabajo(@PathVariable Long id) {
        trabajoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private TrabajoDTO convertirADTO(Trabajo trabajo) {
        TrabajoDTO dto = new TrabajoDTO();
        dto.setId(trabajo.getId());
        dto.setTitulo(trabajo.getTitulo());
        dto.setResumen(trabajo.getResumen());

        // ✅ Manejo correcto de null y tipos de datos
        dto.setAutoresIds(trabajo.getAutores().stream()
                .map(autorTrabajo -> {
                    Congresista congresista = autorTrabajo.getCongresista();
                    return (congresista != null) ? congresista.getId() : null;
                })
                .filter(id -> id != null) // 🔹 Filtrar IDs nulos
                .collect(Collectors.toList()));

        return dto;
    }

    private Trabajo convertirAEntidad(TrabajoDTO dto) {
        Trabajo trabajo = new Trabajo();
        trabajo.setId(dto.getId());
        trabajo.setTitulo(dto.getTitulo());
        trabajo.setResumen(dto.getResumen());
        return trabajo;
    }
}
