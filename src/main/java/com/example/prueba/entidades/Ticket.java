package com.example.prueba.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria;
    private String descripcion;
    private String ubicacion;
    private String dependencia;
    private String prioridad;  // Alta, Media, Baja
    private String estado;     // Nuevo, Asignado, En ejecución, Cerrado
    private String evidencia;
    private LocalDateTime fechaCreacion = LocalDateTime.now();


    private String solicitante;


    private String operario; // quien atiende

    // Getters y Setters
}
