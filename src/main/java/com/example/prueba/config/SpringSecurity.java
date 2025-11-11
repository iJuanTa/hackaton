package com.example.prueba.config;

import com.example.prueba.servicios.UsuarioService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
@EnableWebSecurity
public class SpringSecurity{

    @Autowired
    private UsuarioService usuarioServicio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usuarioServicio);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // <-- DESACTIVAR CSRF PARA TESTEAR CON POSTMAN
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/registro/**", "/login").permitAll()
                        .requestMatchers("/api/admin/**","/swagger-ui/**","/v3/api-docs").hasRole("Administrador")
                        .requestMatchers("/api/tickets/crear").hasRole("Solicitante")
                        .requestMatchers("/api/tickets/ver").hasAnyRole("OficinaMantenimiento","Administrador","Operario")
                        .requestMatchers("/api/tickets/{i}/prioridad").hasRole("OficinaMantenimiento")
                        .requestMatchers("/api/tickets/{i}/asignar-operario").hasRole("Administrador")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.disable()//Desactiva pagina de login
                ).logout(logout -> logout
                        .logoutUrl("/logout")              // endpoint de logout
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.getWriter().write("Sesion cerrada correctamente");
                        })
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }
}
