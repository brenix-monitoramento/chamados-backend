package com.projects.chamados.dtos.inputs;

import com.projects.chamados.enums.OpenTicketStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record OpenTicketInputDTO (
        @NotBlank(message = "O id do chamado é obrigatório.")
        String idChamado,
        @NotNull(message = "O status é obrigatório. Valores aceitos: Em Andamento ou Finalizado.")
        OpenTicketStatus status,
        @NotBlank(message = "A ocorrência é obrigatória.")
        @Size(min=3, max=84, message = "A ocorrência deve ter entre 3 e 84 caracteres.")
        String incident,
        @NotNull(message = "A data de início é obrigatória.")
        LocalDate startDate,
        @NotNull(message = "A hora de início é obrigatória.")
        LocalTime startTime,
        LocalDate endDate,
        LocalTime endTime,
        @Size(max=84, message = "A observação deve ter no máximo 84 caracteres.")
        String observations,
        @NotNull(message = "O id do equipamento é obrigatório.")
        UUID equipmentId,
        @NotNull(message = "O id do técnico é obrigatório.")
        UUID technicianId

) {
}
