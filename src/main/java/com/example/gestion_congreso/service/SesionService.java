package com.example.gestion_congreso.service;

import com.example.gestion_congreso.dto.SesionDTO;
import com.example.gestion_congreso.model.Congresista;
import com.example.gestion_congreso.model.Sesion;
import com.example.gestion_congreso.repository.CongresistaRepository;
import com.example.gestion_congreso.repository.SesionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SesionService {
    private final SesionRepository sesionRepository;
    private final CongresistaRepository congresistaRepository;

    public SesionService(SesionRepository sesionRepository, CongresistaRepository congresistaRepository) {
        this.sesionRepository = sesionRepository;
        this.congresistaRepository = congresistaRepository;
    }

    public List<SesionDTO> obtenerTodas() {
        return sesionRepository.findAll().stream()
                .map(sesion -> new SesionDTO(
                        sesion.getId(),
                        sesion.getSala(),
                        sesion.getFechaHora(),  // ✅ Se mantiene LocalDateTime
                        sesion.getChairman().getId(),
                        sesion.getChairman().getNombre()
                ))
                .collect(Collectors.toList());
    }

    public SesionDTO guardar(SesionDTO sesionDTO) {
        Sesion nuevaSesion = new Sesion();
        nuevaSesion.setSala(sesionDTO.getSala());
        nuevaSesion.setFechaHora(sesionDTO.getFechaHora());

        // ✅ Si hay chairman, asignarlo correctamente
        if (sesionDTO.getChairmanId() != null) {
            Congresista chairman = congresistaRepository.findById(sesionDTO.getChairmanId()).orElse(null);
            nuevaSesion.setChairman(chairman);
        }

        Sesion sesionGuardada = sesionRepository.save(nuevaSesion);

        // ✅ Crear y devolver un DTO con el chairman asignado
        return new SesionDTO(
                sesionGuardada.getId(),
                sesionGuardada.getSala(),
                sesionGuardada.getFechaHora(),
                (sesionGuardada.getChairman() != null) ? sesionGuardada.getChairman().getId() : null,
                (sesionGuardada.getChairman() != null) ? sesionGuardada.getChairman().getNombre() : null
        );
    }


    public void eliminar(Long id) {
        if (!sesionRepository.existsById(id)) {
            throw new RuntimeException("La sesión con ID " + id + " no existe.");
        }
        sesionRepository.deleteById(id);
    }
}
