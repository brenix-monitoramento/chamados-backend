package com.projects.chamados.services;

import com.projects.chamados.dtos.outputs.OpenTicketOutputDTO;
import com.projects.chamados.repositories.OpenTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenTicketService {
    @Autowired
    private OpenTicketRepository openTicketRepository;

    public List<OpenTicketOutputDTO> listAllBySearch (String searchTerm){
        return this.openTicketRepository.findByIdChamadoContainingIgnoreCaseOrEquipment_IdSefitContainingIgnoreCase(searchTerm, searchTerm)
                .stream()
                .map(openTicket -> new OpenTicketOutputDTO(openTicket)).toList();
    }
}
