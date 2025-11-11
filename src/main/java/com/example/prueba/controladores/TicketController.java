package com.example.prueba.controladores;

import com.example.prueba.entidades.Ticket;
import com.example.prueba.entidades.Usuario;
import com.example.prueba.repositorios.TicketRepository;
import com.example.prueba.repositorios.UsuarioRepository;
import com.example.prueba.servicios.TicketService;
import com.example.prueba.dto.CrearTicketDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin("*")
public class TicketController {

    private final TicketService service;
    private final TicketRepository ticketRepository;

    // Inyectamos UsuarioRepository para buscar el operario
    private final UsuarioRepository usuarioRepository;

    public TicketController(TicketService service,
                            TicketRepository ticketRepository,
                            UsuarioRepository usuarioRepository) {
        this.service = service;
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/ver")
    public ResponseEntity<List<Ticket>> verTickets() {
        return ResponseEntity.ok(ticketRepository.findAll());
    }
    @PostMapping("/crear")
    public Ticket crear(@RequestBody CrearTicketDTO dto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        return service.crearTicket(dto,session);
    }


    @PatchMapping("/{id}/prioridad")
    public ResponseEntity<Ticket> actualizarPrioridad(@PathVariable Long id,
                                                      @RequestBody Map<String, Object> updates) {

        // Validamos que venga el campo prioridad
        if (!updates.containsKey("prioridad")) {
            return ResponseEntity.badRequest().build();
        }

        String nuevaPrioridad = updates.get("prioridad").toString();
        if (!List.of("Alta", "Media", "Baja").contains(nuevaPrioridad)) {
            return ResponseEntity.badRequest().build();
        }

        return ticketRepository.findById(id)
                .map(ticket -> {
                    ticket.setPrioridad(nuevaPrioridad);
                    Ticket guardado = ticketRepository.save(ticket);
                    return ResponseEntity.ok(guardado);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @PatchMapping("/{id}/asignar-operario")
    public ResponseEntity<?> asignarOperario(@PathVariable Long id,
                                             @RequestBody Map<String, Object> updates) {

        if (!updates.containsKey("operario")) {
            return ResponseEntity.badRequest().body("Falta el ID del operario");
        }

        Long operarioId = Long.valueOf(updates.get("operario").toString());

        Usuario operario = usuarioRepository.findById(operarioId)
                .orElseThrow(() -> new RuntimeException("Operario no encontrado"));

        // ✅ Validar que el usuario sea realmente un operario
        if (!operario.getRol().getNombre().equalsIgnoreCase("Operario")) {
            return ResponseEntity.badRequest().body("El usuario no tiene rol de Operario");
        }

        // ✅ Asignar el operario al ticket
        return ticketRepository.findById(id)
                .map(ticket -> {
                    ticket.setOperario(operario.getNombre()); // asigna el objeto Usuario, no solo el nombre
                    ticket.setEstado("Asignado");

                    Ticket guardado = ticketRepository.save(ticket);

                    Map<String, Object> response = new HashMap<>();
                    response.put("mensaje", "Operario asignado correctamente");
                    response.put("nombreOperario", operario.getNombre());
                    response.put("ticketId", guardado.getId());

                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}