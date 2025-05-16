package com.citas.repository;

import com.citas.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    List<Servicio> findByActivoservicioTrue();
    List<Servicio> findByNombreservicioContainingIgnoreCase(String nombreservicio);
} 