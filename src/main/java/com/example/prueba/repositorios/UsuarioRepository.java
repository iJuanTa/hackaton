package com.example.prueba.repositorios;

import com.example.prueba.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    public Optional<Usuario> findByNombre(String nombre);

    String nombre(String nombre);
}
