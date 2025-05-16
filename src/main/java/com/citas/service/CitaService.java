package com.citas.service;

import com.citas.model.Cita;
import com.citas.model.Servicio;
import com.citas.repository.CitaRepository;
import com.citas.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Cita> obtenerTodasLasCitas() {
        return citaRepository.findAll();
    }

    public Optional<Cita> obtenerCitaPorId(Long id) {
        return citaRepository.findById(id);
    }

    public List<Cita> obtenerCitasPorCliente(Long idcliente) {
        return citaRepository.findByIdcliente(idcliente);
    }

    public List<Cita> obtenerCitasPorVeterinaria(Long idveterinaria) {
        return citaRepository.findByIdveterinaria(idveterinaria);
    }

    public List<Cita> obtenerCitasPorMascota(Long idmascota) {
        return citaRepository.findByIdmascota(idmascota);
    }

    public Cita crearCita(Cita cita) {
        // Validar que el servicio existe y está activo
        Servicio servicio = servicioRepository.findById(cita.getIdservicio())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        if (!servicio.getActivoservicio()) {
            throw new RuntimeException("El servicio seleccionado no está activo");
        }

        return citaRepository.save(cita);
    }

    public Cita actualizarCita(Long id, Cita citaActualizada) {
        return citaRepository.findById(id)
                .map(cita -> {
                    cita.setIdveterinaria(citaActualizada.getIdveterinaria());
                    cita.setIdcliente(citaActualizada.getIdcliente());
                    cita.setIdmascota(citaActualizada.getIdmascota());
                    cita.setIdservicio(citaActualizada.getIdservicio());
                    cita.setFecha(citaActualizada.getFecha());
                    cita.setHorainicio(citaActualizada.getHorainicio());
                    cita.setHorafinal(citaActualizada.getHorafinal());
                    cita.setEstadocita(citaActualizada.getEstadocita());
                    return citaRepository.save(cita);
                })
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));
    }

    public void eliminarCita(Long id) {
        citaRepository.deleteById(id);
    }
} 
