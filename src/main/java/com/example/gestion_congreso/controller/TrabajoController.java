package com.example.gestion_congreso.controller;

import com.example.gestion_congreso.dto.TrabajoDTO;
import com.example.gestion_congreso.model.Trabajo;
import com.example.gestion_congreso.service.TrabajoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.gestion_congreso.model.Congresista;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trabajos")
public class TrabajoController {
    private final TrabajoService trabajoService;

    public TrabajoController(TrabajoService trabajoService) {
        this.trabajoService = trabajoService;
    }

    @GetMapping
    public List<TrabajoDTO> listarTrabajos() {
        return trabajoService.obtenerTodos()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<TrabajoDTO> registrarTrabajo(@RequestBody TrabajoDTO trabajoDTO) {
        Trabajo trabajo = trabajoService.guardar(convertirAEntidad(trabajoDTO));
        return ResponseEntity.ok(convertirADTO(trabajo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrabajoDTO> obtenerTrabajo(@PathVariable Long id) {
        Trabajo trabajo = trabajoService.obtenerPorId(id);
        return ResponseEntity.ok(convertirADTO(trabajo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrabajoDTO> actualizarTrabajo(@PathVariable Long id, @RequestBody TrabajoDTO trabajoDTO) {
        Trabajo trabajoActualizado = trabajoService.actualizarTrabajo(id, convertirAEntidad(trabajoDTO));
        return ResponseEntity.ok(convertirADTO(trabajoActualizado));
    }

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
