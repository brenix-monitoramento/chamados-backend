package com.projects.chamados.repositories;

import com.projects.chamados.models.OpenTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OpenTicketRepository extends JpaRepository<OpenTicket, UUID> {
    List<OpenTicket> findByIdChamadoContainingIgnoreCaseOrEquipment_IdSefitContainingIgnoreCase(String idChamado, String idSefit);
}
