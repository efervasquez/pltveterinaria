package com.citas.repository;

import com.citas.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByIdcliente(Long idcliente);
    List<Mascota> findByActivomascotaTrue();
    List<Mascota> findByNombremascotaContainingIgnoreCase(String nombre);
    List<Mascota> findByEspeciemascotaContainingIgnoreCase(String especie);
} 