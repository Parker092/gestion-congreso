package com.example.gestion_congreso.repository;

import com.example.gestion_congreso.model.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrabajoRepository extends JpaRepository<Trabajo, Long> {
    List<Trabajo> findByTituloContainingIgnoreCase(String titulo);
}
