package com.citas.controller;

import com.citas.model.Veterinaria;
import com.citas.service.VeterinariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarias")
@CrossOrigin(origins = "*")
public class VeterinariaController {

    @Autowired
    private VeterinariaService veterinariaService;

    @GetMapping
    public List<Veterinaria> obtenerTodasLasVeterinarias() {
        return veterinariaService.obtenerTodasLasVeterinarias();
    }

    @GetMapping("/activas")
    public List<Veterinaria> obtenerVeterinariasActivas() {
        return veterinariaService.obtenerVeterinariasActivas();
    }

    @GetMapping("/buscar")
    public List<Veterinaria> buscarVeterinariasPorNombre(@RequestParam String nombre) {
        return veterinariaService.buscarVeterinariasPorNombre(nombre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veterinaria> obtenerVeterinariaPorId(@PathVariable Long id) {
        return veterinariaService.obtenerVeterinariaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Veterinaria crearVeterinaria(@RequestBody Veterinaria veterinaria) {
        return veterinariaService.crearVeterinaria(veterinaria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veterinaria> actualizarVeterinaria(@PathVariable Long id, @RequestBody Veterinaria veterinaria) {
        try {
            Veterinaria veterinariaActualizada = veterinariaService.actualizarVeterinaria(id, veterinaria);
            return ResponseEntity.ok(veterinariaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVeterinaria(@PathVariable Long id) {
        veterinariaService.eliminarVeterinaria(id);
        return ResponseEntity.ok().build();
    }
} 