package com.example.gestion_congreso.repository;

import com.example.gestion_congreso.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SesionRepository extends JpaRepository<Sesion, Long> {
    List<Sesion> findByChairmanId(Long chairmanId);
}
