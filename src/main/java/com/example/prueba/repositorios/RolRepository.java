package com.example.prueba.repositorios;

import com.example.prueba.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol,Long> {
    public Rol findByNombre(String nombre);
}
