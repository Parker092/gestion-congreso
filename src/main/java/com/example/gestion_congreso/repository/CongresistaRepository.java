package com.example.gestion_congreso.repository;

import com.example.gestion_congreso.model.Congresista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CongresistaRepository extends JpaRepository<Congresista, Long> {
    Optional<Congresista> findByCorreoElectronico(String correoElectronico);
}
