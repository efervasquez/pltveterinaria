package com.citas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "veterinarias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veterinaria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idveterinaria")
    private Long idveterinaria;
    
    @Column(name = "nombreveterinaria", nullable = false, length = 100)
    private String nombreveterinaria;
    
    @Column(name = "direccionveterinaria", nullable = false, length = 200)
    private String direccionveterinaria;
    
    @Column(name = "telefonoveterinaria", length = 20)
    private String telefonoveterinaria;
    
    @Column(name = "emailveterinaria", length = 100)
    private String emailveterinaria;
    
    @Column(name = "activoveterinaria", nullable = false)
    private Boolean activoveterinaria = true;
} 