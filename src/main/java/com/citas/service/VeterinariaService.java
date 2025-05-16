package com.citas.service;

import com.citas.model.Veterinaria;
import com.citas.repository.VeterinariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinariaService {

    @Autowired
    private VeterinariaRepository veterinariaRepository;

    public List<Veterinaria> obtenerTodasLasVeterinarias() {
        return veterinariaRepository.findAll();
    }

    public List<Veterinaria> obtenerVeterinariasActivas() {
        return veterinariaRepository.findByActivoveterinariaTrue();
    }

    public List<Veterinaria> buscarVeterinariasPorNombre(String nombre) {
        return veterinariaRepository.findByNombreveterinariaContainingIgnoreCase(nombre);
    }

    public Optional<Veterinaria> obtenerVeterinariaPorId(Long id) {
        return veterinariaRepository.findById(id);
    }

    public Veterinaria crearVeterinaria(Veterinaria veterinaria) {
        return veterinariaRepository.save(veterinaria);
    }

    public Veterinaria actualizarVeterinaria(Long id, Veterinaria veterinariaActualizada) {
        return veterinariaRepository.findById(id)
                .map(veterinaria -> {
                    veterinaria.setNombreveterinaria(veterinariaActualizada.getNombreveterinaria());
                    veterinaria.setDireccionveterinaria(veterinariaActualizada.getDireccionveterinaria());
                    veterinaria.setTelefonoveterinaria(veterinariaActualizada.getTelefonoveterinaria());
                    veterinaria.setEmailveterinaria(veterinariaActualizada.getEmailveterinaria());
                    veterinaria.setActivoveterinaria(veterinariaActualizada.getActivoveterinaria());
                    return veterinariaRepository.save(veterinaria);
                })
                .orElseThrow(() -> new RuntimeException("Veterinaria no encontrada con id: " + id));
    }

    public void eliminarVeterinaria(Long id) {
        veterinariaRepository.deleteById(id);
    }
} 