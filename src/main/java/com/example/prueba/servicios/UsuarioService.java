package com.example.prueba.servicios;

import com.example.prueba.dto.UsuarioDTO;
import com.example.prueba.entidades.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;



public interface UsuarioService extends  UserDetailsService{

    public Usuario saveUsuario(UsuarioDTO usuarioDTO);

    public void deleteUsuario(Long id);
}
