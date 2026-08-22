package com.projects.chamados.services;

import com.projects.chamados.dtos.outputs.OpenTicketOutputDTO;
import com.projects.chamados.exceptions.NotFoundException;
import com.projects.chamados.models.OpenTicket;
import com.projects.chamados.repositories.OpenTicketRepository;
import com.projects.chamados.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OpenTicketService {
    @Autowired
    private OpenTicketRepository openTicketRepository;

    private OpenTicket findIfExists(UUID openTicketId){
        return this.openTicketRepository.findById(openTicketId).orElseThrow(() -> new NotFoundException(Constants.OPEN_TICKET_NOT_FOUND));
    }

    public List<OpenTicketOutputDTO> listAllBySearch (String searchTerm){
        return this.openTicketRepository.findByIdChamadoContainingIgnoreCaseOrEquipment_IdSefitContainingIgnoreCase(searchTerm, searchTerm)
                .stream()
                .map(openTicket -> new OpenTicketOutputDTO(openTicket)).toList();
    }

    public void deleteById(UUID openTicketId){
        this.findIfExists(openTicketId);

        this.openTicketRepository.deleteById(openTicketId);
    }
}
