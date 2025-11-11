package com.example.prueba.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // "Solicitante", "Operario", "Oficina"

    // Getters y Setters
}

