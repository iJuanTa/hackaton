package com.example.prueba.dto;

import java.util.Collection;

import com.example.prueba.entidades.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String correo;
    private String password;
    private Rol rol;

    public UsuarioDTO(String nombre, String password, Rol rol) {
        super();
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;

    }


}

