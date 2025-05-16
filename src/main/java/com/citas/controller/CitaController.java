package com.citas.controller;

import com.citas.model.Cita;
import com.citas.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public List<Cita> obtenerTodasLasCitas() {
        return citaService.obtenerTodasLasCitas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerCitaPorId(@PathVariable Long id) {
        return citaService.obtenerCitaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{idcliente}")
    public List<Cita> obtenerCitasPorCliente(@PathVariable Long idcliente) {
        return citaService.obtenerCitasPorCliente(idcliente);
    }

    @GetMapping("/veterinaria/{idveterinaria}")
    public List<Cita> obtenerCitasPorVeterinaria(@PathVariable Long idveterinaria) {
        return citaService.obtenerCitasPorVeterinaria(idveterinaria);
    }

    @GetMapping("/mascota/{idmascota}")
    public List<Cita> obtenerCitasPorMascota(@PathVariable Long idmascota) {
        return citaService.obtenerCitasPorMascota(idmascota);
    }

    @PostMapping
    public Cita crearCita(@RequestBody Cita cita) {
        return citaService.crearCita(cita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizarCita(@PathVariable Long id, @RequestBody Cita cita) {
        try {
            Cita citaActualizada = citaService.actualizarCita(id, cita);
            return ResponseEntity.ok(citaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Long id) {
        citaService.eliminarCita(id);
        return ResponseEntity.ok().build();
    }
} 