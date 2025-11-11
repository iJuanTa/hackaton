package com.example.prueba.repositorios;

import com.example.prueba.entidades.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
