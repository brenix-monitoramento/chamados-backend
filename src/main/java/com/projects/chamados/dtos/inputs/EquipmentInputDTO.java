package com.projects.chamados.dtos.inputs;

import com.projects.chamados.enums.EquipmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record EquipmentInputDTO
    (
    @NotBlank(message = "A localização é obrigatória.")
    @Size(min=3, max=84, message = "A localização deve ter entre 3 e 84 caracteres.")
    String location,
    @NotBlank(message = "O id Sefit é obrigatório.")
    @Size(min=3, max=20, message = "O id Sefit deve ter entre 3 e 20 caracteres.")
    String idSefit,
    @NotNull(message = "O tipo de equipamento é obrigatório. Valores aceitos: WIM ou OCR.")
    EquipmentType type,
    @NotBlank(message = "O número de série é obrigatório.")
    @Size(min=3, max=25, message = "O número de série deve ter entre 3 e 25 caracteres.")
    String serialNumber,
    @NotNull(message = "O id do técnico é obrigatório.")
    UUID technicianId
){
}
