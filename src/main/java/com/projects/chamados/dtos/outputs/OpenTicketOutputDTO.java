package com.projects.chamados.dtos.outputs;

import com.projects.chamados.enums.EquipmentType;
import com.projects.chamados.enums.OpenTicketStatus;
import com.projects.chamados.models.OpenTicket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record OpenTicketOutputDTO(
        UUID id,
        String idChamado,
        UUID equipmentId,
        String equipmentIdSefit,
        String equipmentLocation,
        EquipmentType equipmentType,
        OpenTicketStatus status,
        String incident,
        LocalDate startDate,
        LocalTime startTime,
        LocalDate endDate,
        LocalTime endTime,
        String observations
) {
    public OpenTicketOutputDTO(OpenTicket openTicket){
        this(
                openTicket.getId(),
                openTicket.getIdChamado(),
                openTicket.getEquipment().getId(),
                openTicket.getEquipment().getIdSefit(),
                openTicket.getEquipment().getLocation(),
                openTicket.getEquipment().getType(),
                openTicket.getStatus(),
                openTicket.getIncident(),
                openTicket.getStartDate(),
                openTicket.getStartTime(),
                openTicket.getEndDate(),
                openTicket.getEndTime(),
                openTicket.getObservations()
        );
    }
}
