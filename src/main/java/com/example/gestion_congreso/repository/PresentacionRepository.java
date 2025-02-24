package com.example.gestion_congreso.repository;

import com.example.gestion_congreso.model.Presentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresentacionRepository extends JpaRepository<Presentacion, Long> {
    List<Presentacion> findBySesionId(Long sesionId);
}
