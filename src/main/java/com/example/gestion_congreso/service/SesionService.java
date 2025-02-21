package com.example.gestion_congreso.service;

import com.example.gestion_congreso.model.Sesion;
import com.example.gestion_congreso.repository.SesionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SesionService {
    private final SesionRepository sesionRepository;

    public SesionService(SesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    public List<Sesion> obtenerTodas() {
        return sesionRepository.findAll();
    }

    public Optional<Sesion> obtenerPorId(Long id) {
        return sesionRepository.findById(id);
    }

    public Sesion guardar(Sesion sesion) {
        return sesionRepository.save(sesion);
    }
}
