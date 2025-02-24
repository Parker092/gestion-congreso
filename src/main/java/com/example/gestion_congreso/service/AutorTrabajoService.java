package com.example.gestion_congreso.service;

import com.example.gestion_congreso.model.AutorTrabajo;
import com.example.gestion_congreso.repository.AutorTrabajoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorTrabajoService {
    private final AutorTrabajoRepository autorTrabajoRepository;

    public AutorTrabajoService(AutorTrabajoRepository autorTrabajoRepository) {
        this.autorTrabajoRepository = autorTrabajoRepository;
    }

    public List<AutorTrabajo> obtenerTodos() {
        return autorTrabajoRepository.findAll();
    }

    public AutorTrabajo guardar(AutorTrabajo autorTrabajo) {
        return autorTrabajoRepository.save(autorTrabajo);
    }
}
