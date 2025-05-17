package com.citas.service;

import com.citas.dto.ClienteDTO;
import com.citas.messaging.CitaEmailNotification;
import com.citas.messaging.MessagePublisher;
import com.citas.model.Cita;
import com.citas.model.Servicio;
import com.citas.repository.CitaRepository;
import com.citas.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {
    private static final Logger logger = LoggerFactory.getLogger(CitaService.class);
    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private ClienteService clienteService;

    private final MessagePublisher messagePublisher;


    public CitaService(CitaRepository repository , MessagePublisher messagePublisher) {
        this.citaRepository = repository;
        this.messagePublisher = messagePublisher;
    }

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

//        String correo = clienteService.obtenerCorreoCliente(cita.getIdcliente());
//
//        if (correo == null ) {
//            throw new RuntimeException("El cliente no tiene registrado su correo electronico");
//        }

        ClienteDTO cliente = clienteService.ListarxCodigo(cita.getIdcliente());

        if (cliente == null ) {
            throw new RuntimeException("El cliente no existe");
        }

        if (cliente.getCorreo() == null ) {
            throw new RuntimeException("El cliente no tiene registrado su correo electronico");
        }

        Cita savedCita = citaRepository.save(cita);
        logger.info("cita created successfully: {}", savedCita.getIdcita());

        try {
            // Create email notification
            CitaEmailNotification notification = CitaEmailNotification.fromCita(savedCita, cliente);

            // Send notification to RabbitMQ (o simplemente registra si RabbitMQ no está disponible)
            messagePublisher.publishEmailNotification(notification);

        } catch (Exception e) {
            // Log the error but don't fail the user creation
            logger.error("Failed to process email notification: {}", e.getMessage());
        }

        return  savedCita;
    }

    public Cita actualizarCita(Long id, Cita citaActualizada) {

        ClienteDTO cliente = clienteService.ListarxCodigo(citaActualizada.getIdcliente());

        if (cliente == null ) {
            throw new RuntimeException("El cliente no existe");
        }

        if (cliente.getCorreo() == null ) {
            throw new RuntimeException("El cliente no tiene registrado su correo electronico");
        }

        Cita citaEncontrada = citaRepository.findById(id)
                .map(cita -> {
                    cita.setIdveterinaria(citaActualizada.getIdveterinaria());
                    cita.setIdcliente(citaActualizada.getIdcliente());
                    cita.setIdmascota(citaActualizada.getIdmascota());
                    cita.setIdservicio(citaActualizada.getIdservicio());
                    cita.setFecha(citaActualizada.getFecha());
                    cita.setHorainicio(citaActualizada.getHorainicio());
                    cita.setHorafinal(citaActualizada.getHorafinal());
                    cita.setEstadocita(citaActualizada.getEstadocita());
                    return cita;
                })
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));

        Cita savedCita = citaRepository.save(citaEncontrada);
        logger.info("cita update successfully: {}", savedCita.getIdcita());

        try {
            // Create email notification
            CitaEmailNotification notification = CitaEmailNotification.fromCita(savedCita, cliente);

            // Send notification to RabbitMQ (o simplemente registra si RabbitMQ no está disponible)
            messagePublisher.publishEmailNotification(notification);

        } catch (Exception e) {
            // Log the error but don't fail the user creation
            logger.error("Failed to process email notification: {}", e.getMessage());
        }

        return savedCita;
    }

    public void eliminarCita(Long id) {
        citaRepository.deleteById(id);
    }
} 
