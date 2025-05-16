package com.citas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mascotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmascota")
    private Long idmascota;
    
    @Column(name = "idcliente", nullable = false)
    private Long idcliente;
    
    @Column(name = "nombremascota", nullable = false, length = 100)
    private String nombremascota;
    
    @Column(name = "especiemascota", nullable = false, length = 50)
    private String especiemascota;
    
    @Column(name = "razamascota", length = 50)
    private String razamascota;
    
    @Column(name = "edadmascota")
    private Integer edadmascota;
    
    @Column(name = "pesomascota")
    private Double pesomascota;
    
    @Column(name = "activomascota", nullable = false)
    private Boolean activomascota = true;
} 