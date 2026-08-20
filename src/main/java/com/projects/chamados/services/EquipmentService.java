package com.projects.chamados.services;

import com.projects.chamados.dtos.inputs.EquipmentInputDTO;
import com.projects.chamados.dtos.outputs.EquipmentOutputDTO;
import com.projects.chamados.exceptions.NotFoundException;
import com.projects.chamados.models.Equipment;
import com.projects.chamados.models.Technician;
import com.projects.chamados.repositories.EquipmentRepository;
import com.projects.chamados.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<EquipmentOutputDTO> listAll(){
        return this.equipmentRepository.findAll().stream()
                .map(equipment -> new EquipmentOutputDTO(equipment))
                .toList();
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

    public void deleteById(UUID equipmentId){
        var equipment = this.findIfExists(equipmentId);

        this.equipmentRepository.deleteById(equipment.getId());
    }
}
