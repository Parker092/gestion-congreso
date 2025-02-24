package com.example.gestion_congreso.service;

import com.example.gestion_congreso.model.Presentacion;
import com.example.gestion_congreso.repository.PresentacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PresentacionService {
    private final PresentacionRepository presentacionRepository;

    public PresentacionService(PresentacionRepository presentacionRepository) {
        this.presentacionRepository = presentacionRepository;
    }

    public List<Presentacion> obtenerTodas() {
        return presentacionRepository.findAll();
    }

    public Optional<Presentacion> obtenerPorId(Long id) {
        return presentacionRepository.findById(id);
    }

    public Presentacion guardar(Presentacion presentacion) {
        return presentacionRepository.save(presentacion);
    }
}
