package com.citas.repository;

import com.citas.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    // Aquí podemos agregar métodos personalizados si los necesitamos
    // Por ejemplo, buscar citas por cliente
    List<Cita> findByIdcliente(Long idcliente);
    
    // Buscar citas por veterinaria
    List<Cita> findByIdveterinaria(Long idveterinaria);
    
    // Buscar citas por mascota
    List<Cita> findByIdmascota(Long idmascota);
} 