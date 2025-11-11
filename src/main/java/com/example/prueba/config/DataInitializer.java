package com.example.prueba.config;


import com.example.prueba.entidades.Rol;
import com.example.prueba.entidades.Usuario;
import com.example.prueba.repositorios.RolRepository;
import com.example.prueba.repositorios.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
public class DataInitializer {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RolRepository rolRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void initData() {
        // Crear roles si no existen
        if (rolRepository.count() == 0) {
            Rol solicitante = new Rol();
            solicitante.setNombre("Solicitante");

            Rol operario = new Rol();
            operario.setNombre("Operario");

            Rol oficina = new Rol();
            oficina.setNombre("OficinaMantenimiento");

            Rol admin = new Rol();
            admin.setNombre("Administrador");

            rolRepository.saveAll(List.of(solicitante, operario, oficina, admin));
        }

        // Crear usuarios si no existen
        if (usuarioRepository.count() == 0) {
            Rol rolSolicitante = rolRepository.findByNombre("Solicitante");
            Rol rolOperario = rolRepository.findByNombre("Operario");
            Rol rolOficina = rolRepository.findByNombre("OficinaMantenimiento");
            Rol rolAdmin = rolRepository.findByNombre("Administrador");

            Usuario u1 = new Usuario();
            u1.setNombre("Carlos Perez");
            u1.setCorreo("carlos@empresa.com");
            u1.setRol(rolSolicitante);
            u1.setPassword(passwordEncoder.encode("1234"));

            Usuario u2 = new Usuario();
            u2.setNombre("Maria Gomez");
            u2.setCorreo("maria@empresa.com");
            u2.setRol(rolOperario);
            u2.setPassword(passwordEncoder.encode("1234"));

            Usuario u3 = new Usuario();
            u3.setNombre("Lucia Torres");
            u3.setCorreo("lucia@empresa.com");
            u3.setRol(rolOficina);
            u3.setPassword(passwordEncoder.encode("1234"));

            Usuario u4 = new Usuario();
            u4.setNombre("Admin SGML");
            u4.setCorreo("admin@empresa.com");
            u4.setRol(rolAdmin);
            u4.setPassword(passwordEncoder.encode("1234"));

            Usuario u5 = new Usuario();
            u5.setNombre("Juan Villalba");
            u5.setRol(rolSolicitante);
            u5.setCorreo("manuel@empresa.com");
            u5.setPassword(passwordEncoder.encode("1234"));

            Usuario u6 = new Usuario();
            u6.setNombre("Santiago Marquez");
            u6.setCorreo("santiago@empresa.com");
            u6.setRol(rolSolicitante);
            u6.setPassword(passwordEncoder.encode("1234"));

            Usuario u7 = new Usuario();
            u7.setNombre("Juan Tabares");
            u7.setCorreo("juan@empresa.com");
            u7.setRol(rolAdmin);
            u7.setPassword(passwordEncoder.encode("1234"));

            Usuario u8 = new Usuario();
            u2.setNombre("Santiago Calderon");
            u2.setCorreo("caldoparado@empresa.com");
            u2.setRol(rolOperario);
            u2.setPassword(passwordEncoder.encode("1234"));

            usuarioRepository.saveAll(List.of(u1, u2, u3, u4));
        }
    }
}
