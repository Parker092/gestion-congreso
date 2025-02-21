package com.example.gestion_congreso.service;

import com.example.gestion_congreso.exception.NotFoundException;
import com.example.gestion_congreso.model.Trabajo;
import com.example.gestion_congreso.repository.TrabajoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajoService {
    private final TrabajoRepository trabajoRepository;

    public TrabajoService(TrabajoRepository trabajoRepository) {
        this.trabajoRepository = trabajoRepository;
    }

    public List<Trabajo> obtenerTodos() {
        return trabajoRepository.findAll();
    }

    public Trabajo obtenerPorId(Long id) {
        return trabajoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Trabajo con ID " + id + " no encontrado"));
    }

    public Trabajo guardar(Trabajo trabajo) {
        return trabajoRepository.save(trabajo);
    }

    public Trabajo actualizarTrabajo(Long id, Trabajo trabajoActualizado) {
        Trabajo trabajoExistente = obtenerPorId(id);
        trabajoExistente.setTitulo(trabajoActualizado.getTitulo());
        trabajoExistente.setResumen(trabajoActualizado.getResumen());
        return trabajoRepository.save(trabajoExistente);
    }

    public void eliminar(Long id) {
        Trabajo trabajo = obtenerPorId(id);
        trabajoRepository.delete(trabajo);
    }
}
