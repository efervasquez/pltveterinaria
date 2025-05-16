package com.citas.repository;

import com.citas.model.Veterinaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeterinariaRepository extends JpaRepository<Veterinaria, Long> {
    List<Veterinaria> findByActivoveterinariaTrue();
    List<Veterinaria> findByNombreveterinariaContainingIgnoreCase(String nombre);
} 