package com.citas.service;

import com.citas.model.Mascota;
import com.citas.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotaRepository.findAll();
    }

    public List<Mascota> obtenerMascotasActivas() {
        return mascotaRepository.findByActivomascotaTrue();
    }

    public List<Mascota> obtenerMascotasPorCliente(Long idcliente) {
        return mascotaRepository.findByIdcliente(idcliente);
    }

    public List<Mascota> buscarMascotasPorNombre(String nombre) {
        return mascotaRepository.findByNombremascotaContainingIgnoreCase(nombre);
    }

    public List<Mascota> buscarMascotasPorEspecie(String especie) {
        return mascotaRepository.findByEspeciemascotaContainingIgnoreCase(especie);
    }

    public Optional<Mascota> obtenerMascotaPorId(Long id) {
        return mascotaRepository.findById(id);
    }

    public Mascota crearMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public Mascota actualizarMascota(Long id, Mascota mascotaActualizada) {
        return mascotaRepository.findById(id)
                .map(mascota -> {
                    mascota.setIdcliente(mascotaActualizada.getIdcliente());
                    mascota.setNombremascota(mascotaActualizada.getNombremascota());
                    mascota.setEspeciemascota(mascotaActualizada.getEspeciemascota());
                    mascota.setRazamascota(mascotaActualizada.getRazamascota());
                    mascota.setEdadmascota(mascotaActualizada.getEdadmascota());
                    mascota.setPesomascota(mascotaActualizada.getPesomascota());
                    mascota.setActivomascota(mascotaActualizada.getActivomascota());
                    return mascotaRepository.save(mascota);
                })
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + id));
    }

    public void eliminarMascota(Long id) {
        mascotaRepository.deleteById(id);
    }
} 