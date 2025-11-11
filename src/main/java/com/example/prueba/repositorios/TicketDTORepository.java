package com.example.prueba.repositorios;

import com.example.prueba.dto.CrearTicketDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TicketDTORepository extends JpaRepository<CrearTicketDTO, Long> {
}
