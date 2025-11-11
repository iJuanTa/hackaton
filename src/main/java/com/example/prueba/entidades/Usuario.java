package com.example.prueba.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correo;
    private String password;

    @ManyToOne
    private Rol rol;

    // Getters y Setters
}

