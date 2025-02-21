package com.example.gestion_congreso.repository;

import com.example.gestion_congreso.model.AutorTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorTrabajoRepository extends JpaRepository<AutorTrabajo, Long> {
    List<AutorTrabajo> findByTrabajoId(Long trabajoId);
    List<AutorTrabajo> findByCongresistaId(Long congresistaId);
}
