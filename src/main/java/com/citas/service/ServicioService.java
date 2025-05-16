package com.citas.service;

import com.citas.model.Servicio;
import com.citas.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> obtenerTodosLosServicios() {
        return servicioRepository.findAll();
    }

    public List<Servicio> obtenerServiciosActivos() {
        return servicioRepository.findByActivoservicioTrue();
    }

    public List<Servicio> buscarServiciosPorNombre(String nombre) {
        return servicioRepository.findByNombreservicioContainingIgnoreCase(nombre);
    }

    public Optional<Servicio> obtenerServicioPorId(Long id) {
        return servicioRepository.findById(id);
    }

    public Servicio crearServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public Servicio actualizarServicio(Long id, Servicio servicioActualizado) {
        return servicioRepository.findById(id)
                .map(servicio -> {
                    servicio.setNombreservicio(servicioActualizado.getNombreservicio());
                    servicio.setPrecioservicio(servicioActualizado.getPrecioservicio());
                    servicio.setActivoservicio(servicioActualizado.getActivoservicio());
                    return servicioRepository.save(servicio);
                })
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con id: " + id));
    }

    public void eliminarServicio(Long id) {
        servicioRepository.deleteById(id);
    }
} 