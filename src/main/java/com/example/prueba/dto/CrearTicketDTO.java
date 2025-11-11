package com.example.prueba.dto;

import com.example.prueba.entidades.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class CrearTicketDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria;
    private String descripcion;
    private String ubicacion;
    private String dependencia;
    private String evidencia;
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @OneToOne
    private Usuario solicitante;
}
