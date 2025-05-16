package com.citas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idservicio")
    private Long idservicio;
    
    @Column(name = "idveterinaria", nullable = false)
    private Long idveterinaria;

    @Column(name = "nombreservicio", nullable = false, length = 100)
    private String nombreservicio;
    
    @Column(name = "precioservicio", nullable = false)
    private Double precioservicio;
    
    @Column(name = "activoservicio", nullable = false)
    private Boolean activoservicio = true;
} 