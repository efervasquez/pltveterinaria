package com.citas.controller;

import com.citas.model.Servicio;
import com.citas.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
@CrossOrigin(origins = "*")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public List<Servicio> obtenerTodosLosServicios() {
        return servicioService.obtenerTodosLosServicios();
    }

    @GetMapping("/activos")
    public List<Servicio> obtenerServiciosActivos() {
        return servicioService.obtenerServiciosActivos();
    }

    @GetMapping("/buscar")
    public List<Servicio> buscarServiciosPorNombre(@RequestParam String nombre) {
        return servicioService.buscarServiciosPorNombre(nombre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtenerServicioPorId(@PathVariable Long id) {
        return servicioService.obtenerServicioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Servicio crearServicio(@RequestBody Servicio servicio) {
        return servicioService.crearServicio(servicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> actualizarServicio(@PathVariable Long id, @RequestBody Servicio servicio) {
        try {
            Servicio servicioActualizado = servicioService.actualizarServicio(id, servicio);
            return ResponseEntity.ok(servicioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {
        servicioService.eliminarServicio(id);
        return ResponseEntity.ok().build();
    }
} 