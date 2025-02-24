package com.example.gestion_congreso.service;

import com.example.gestion_congreso.model.Congresista;
import com.example.gestion_congreso.repository.CongresistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CongresistaService {
    private final CongresistaRepository congresistaRepository;

    public CongresistaService(CongresistaRepository congresistaRepository) {
        this.congresistaRepository = congresistaRepository;
    }

    public List<Congresista> obtenerTodos() {
        return congresistaRepository.findAll();
    }

    public Optional<Congresista> obtenerPorId(Long id) {
        return congresistaRepository.findById(id);
    }

    public Congresista guardar(Congresista congresista) {
        return congresistaRepository.save(congresista);
    }

    public void eliminar(Long id) {
        congresistaRepository.deleteById(id);
    }
}
