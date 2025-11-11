package com.example.prueba.servicios;



import com.example.prueba.dto.CrearTicketDTO;
import com.example.prueba.dto.LoginUsuario;
import com.example.prueba.dto.UsuarioDTO;
import com.example.prueba.entidades.Ticket;
import com.example.prueba.entidades.Usuario;
import com.example.prueba.repositorios.TicketRepository;
import com.example.prueba.repositorios.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository repo;
    private final UsuarioRepository usuarioRepository;


    public TicketService(TicketRepository repo, UsuarioRepository usuarioRepository) {
        this.repo = repo;
        this.usuarioRepository = usuarioRepository;
    }

    public Ticket crearTicket(CrearTicketDTO dto, HttpSession session) {


        Ticket t = new Ticket();
        t.setCategoria(dto.getCategoria());
        t.setDescripcion(dto.getDescripcion());
        t.setUbicacion(dto.getUbicacion());
        t.setEvidencia(dto.getEvidencia());
        t.setEstado("Nuevo");
        t.setSolicitante((String)session.getAttribute("username"));

        return repo.save(t);
    }

    public List<Ticket> listarTickets() {
        return repo.findAll();
    }

    public Ticket asignarOperario(Long id, Long idOperario) {

        Ticket t = repo.findById(id).orElseThrow();
        t.setEstado("Asignado");
        // Aquí podrías buscar el operario en UsuarioRepository si lo deseas
        return repo.save(t);
    }

    public Ticket actualizarEstado(Long id, String nuevoEstado) {
        Ticket t = repo.findById(id).orElseThrow();
        t.setEstado(nuevoEstado);
        return repo.save(t);
    }


}

