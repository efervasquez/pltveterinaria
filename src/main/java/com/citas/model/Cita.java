package com.citas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcita")
    private Long idcita;
    
    @Column(name = "idveterinaria", nullable = false)
    private Long idveterinaria;

    @Column(name = "idcliente", nullable = false)
    private Long idcliente;

    @Column(name = "idmascota", nullable = false)
    private Long idmascota;
    
    @Column(name = "idservicio", nullable = false)
    private Long idservicio;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;
    
    @Column(name = "horainicio", nullable = false)
    private LocalTime horainicio;
    
    @Column(name = "horafinal", nullable = false)
    private LocalTime horafinal;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estadocita", nullable = false)
    private EstadoCita estadocita = EstadoCita.PENDIENTE;
} 