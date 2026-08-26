package com.projects.chamados.services;

import com.projects.chamados.dtos.inputs.OpenTicketInputDTO;
import com.projects.chamados.dtos.outputs.OpenTicketOutputDTO;
import com.projects.chamados.exceptions.ConflictException;
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
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private TechnicianService technicianService;

    private OpenTicket findIfExists(UUID openTicketId){
        return this.openTicketRepository.findById(openTicketId).orElseThrow(() -> new NotFoundException(Constants.OPEN_TICKET_NOT_FOUND));
    }

    private void findIfIdChamadoAlreadyExists(String idChamado){
        boolean exists =  this.openTicketRepository.existsByIdChamado(idChamado);

        if(exists) throw new ConflictException(Constants.ID_CHAMADO_ALREADY_EXISTS);
    }

    public List<OpenTicketOutputDTO> listAllBySearch (String searchTerm){
        return this.openTicketRepository.findByIdChamadoContainingIgnoreCaseOrEquipment_IdSefitContainingIgnoreCaseOrderByIdChamadoDesc(searchTerm, searchTerm)
                .stream()
                .map(openTicket -> new OpenTicketOutputDTO(openTicket)).toList();
    }

    public OpenTicketOutputDTO create(OpenTicketInputDTO openTicket){
        this.findIfIdChamadoAlreadyExists(openTicket.idChamado());
        var technician = this.technicianService.findIfExists(openTicket.technicianId());
        var equipment = this.equipmentService.findIfExists(openTicket.equipmentId());

        var createdOpenTicket = new OpenTicket();
        createdOpenTicket.setIdChamado(openTicket.idChamado());
        createdOpenTicket.setStatus(openTicket.status());
        createdOpenTicket.setIncident(openTicket.incident());
        createdOpenTicket.setStartDate(openTicket.startDate());
        createdOpenTicket.setStartTime(openTicket.startTime());
        createdOpenTicket.setEndDate(openTicket.endDate());
        createdOpenTicket.setEndTime(openTicket.endTime());
        createdOpenTicket.setObservations(openTicket.observations());

        createdOpenTicket.setTechnician(technician);
        createdOpenTicket.setEquipment(equipment);

        this.openTicketRepository.save(createdOpenTicket);

        return new OpenTicketOutputDTO(createdOpenTicket);
    }

    public void deleteById(UUID openTicketId){
        this.findIfExists(openTicketId);

        this.openTicketRepository.deleteById(openTicketId);
    }
}
