package com.projects.chamados.services;

import com.projects.chamados.dtos.inputs.EquipmentInputDTO;
import com.projects.chamados.dtos.outputs.EquipmentListOutputDTO;
import com.projects.chamados.dtos.outputs.EquipmentOutputDTO;
import com.projects.chamados.exceptions.NotFoundException;
import com.projects.chamados.models.Equipment;
import com.projects.chamados.models.Technician;
import com.projects.chamados.repositories.EquipmentRepository;
import com.projects.chamados.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private TechnicianService technicianService;

    private Equipment findIfExists(UUID equipmentId){
       return this.equipmentRepository.findById(equipmentId).orElseThrow(() -> new NotFoundException(Constants.EQUIPMENT_NOT_FOUND));
    }

    public EquipmentListOutputDTO listAllBySearch(String searchTerm){
        var equipmentList = this.equipmentRepository.findByLocationContainingIgnoreCaseOrIdSefitContainingIgnoreCase(searchTerm, searchTerm)
                .stream()
                .map(equipment -> new EquipmentOutputDTO(equipment))
                .toList();

        return new EquipmentListOutputDTO(equipmentList);
    }

    public EquipmentOutputDTO create(EquipmentInputDTO equipment){
        this.technicianService.findIfExists(equipment.technicianId());

        var createdEquipment = new Equipment();
        createdEquipment.setLocation(equipment.location());
        createdEquipment.setType(equipment.type());
        createdEquipment.setIdSefit(equipment.idSefit());
        createdEquipment.setSerialNumber(equipment.serialNumber());

        var technician = new Technician();
        technician.setId(equipment.technicianId());
        createdEquipment.setTechnician(technician);

        this.equipmentRepository.save(createdEquipment);

        return new EquipmentOutputDTO(createdEquipment);
    }

    public EquipmentOutputDTO updateById(UUID equipmentId, EquipmentInputDTO equipment){
        this.findIfExists(equipmentId);
        this.technicianService.findIfExists(equipment.technicianId());

        var updatedEquipment = new Equipment();
        updatedEquipment.setId(equipmentId);
        updatedEquipment.setLocation(equipment.location());
        updatedEquipment.setType(equipment.type());
        updatedEquipment.setIdSefit(equipment.idSefit());
        updatedEquipment.setSerialNumber(equipment.serialNumber());

        var technician = new Technician();
        technician.setId(equipment.technicianId());
        updatedEquipment.setTechnician(technician);

        this.equipmentRepository.save(updatedEquipment);

        return new EquipmentOutputDTO(updatedEquipment);
    }

    public void deleteById(UUID equipmentId){
        var equipment = this.findIfExists(equipmentId);

        this.equipmentRepository.deleteById(equipment.getId());
    }
}
