package com.projects.chamados.dtos.outputs;

import java.util.List;

public record EquipmentListOutputDTO(
        Integer total,
        List<EquipmentOutputDTO> items
) {
    public EquipmentListOutputDTO(List<EquipmentOutputDTO> equipments){
        this(equipments.size(), equipments);
    }
}
