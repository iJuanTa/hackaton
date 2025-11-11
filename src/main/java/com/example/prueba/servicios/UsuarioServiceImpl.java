package com.example.prueba.servicios;

import com.example.prueba.dto.UsuarioDTO;
import com.example.prueba.entidades.Usuario;
import com.example.prueba.repositorios.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
private  final PasswordEncoder passwordEncoder;
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String obtenerUsuario(UsuarioDTO usuarioDTO) {
        return usuarioDTO.getNombre();
    }
    @Override
    public Usuario saveUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setPassword(usuarioDTO.getPassword()); // asegúrate de encriptarla después
        usuario.setRol(usuarioDTO.getRol());
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Implementación del método de UserDetailsService (para Spring Security)
    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombre(nombre)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + nombre));

        // Retorna un UserDetails con el rol del usuario
        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getNombre())
                .password(usuario.getPassword())
                .roles(usuario.getRol().getNombre()) // o authorities(...)
                .build();
    }
}

