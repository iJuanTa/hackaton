package com.example.prueba.controladores;


import com.example.prueba.dto.LoginUsuario;
import com.example.prueba.dto.UsuarioDTO;
import com.example.prueba.entidades.Usuario;
import com.example.prueba.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class UsuarioController {


    private UsuarioService usuarioService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, AuthenticationManager authenticationManager) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUsuario loginUsuario, HttpServletRequest request){
        try {
            // Crear token con credenciales enviadas
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Crear sesión manualmente
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            // Si llega aquí, la autenticación fue exitosa
            return ResponseEntity.ok("Login exitoso para usuario: " + authentication.getName());
        } catch (Exception e) {
            // Si las credenciales son incorrectas
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

    @DeleteMapping("/registro/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return "✅ Logout exitoso. Sesión cerrada";
    }
}