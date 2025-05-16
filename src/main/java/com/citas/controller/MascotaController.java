package com.citas.controller;

import com.citas.model.Mascota;
import com.citas.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "*")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping
    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotaService.obtenerTodasLasMascotas();
    }

    @GetMapping("/activas")
    public List<Mascota> obtenerMascotasActivas() {
        return mascotaService.obtenerMascotasActivas();
    }

    @GetMapping("/cliente/{idcliente}")
    public List<Mascota> obtenerMascotasPorCliente(@PathVariable Long idcliente) {
        return mascotaService.obtenerMascotasPorCliente(idcliente);
    }

    @GetMapping("/buscar/nombre")
    public List<Mascota> buscarMascotasPorNombre(@RequestParam String nombre) {
        return mascotaService.buscarMascotasPorNombre(nombre);
    }

    @GetMapping("/buscar/especie")
    public List<Mascota> buscarMascotasPorEspecie(@RequestParam String especie) {
        return mascotaService.buscarMascotasPorEspecie(especie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerMascotaPorId(@PathVariable Long id) {
        return mascotaService.obtenerMascotaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mascota crearMascota(@RequestBody Mascota mascota) {
        return mascotaService.crearMascota(mascota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizarMascota(@PathVariable Long id, @RequestBody Mascota mascota) {
        try {
            Mascota mascotaActualizada = mascotaService.actualizarMascota(id, mascota);
            return ResponseEntity.ok(mascotaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return ResponseEntity.ok().build();
    }
} 