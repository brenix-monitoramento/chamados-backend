package com.projects.chamados.services;

import com.projects.chamados.dtos.inputs.TechnicianInputDTO;
import com.projects.chamados.dtos.outputs.TechnicianOutputDTO;
import com.projects.chamados.exceptions.NotFoundException;
import com.projects.chamados.models.Technician;
import com.projects.chamados.repositories.TechnicianRepository;
import com.projects.chamados.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TechnicianService {
    @Autowired
    private TechnicianRepository technicianRepository;

    public Technician findIfExists(UUID technicianId){
        return this.technicianRepository.findById(technicianId).orElseThrow(() -> new NotFoundException(Constants.TECHNICIAN_NOT_FOUND));
    }

    public List<TechnicianOutputDTO> listAll(){
        return this.technicianRepository.findAllWithoutPassword();
    }

    public TechnicianOutputDTO updateById(UUID technicianId, TechnicianInputDTO technician){
        Technician updatedTechnician =  this.findIfExists(technicianId);

       updatedTechnician.setName(technician.name());
       updatedTechnician.setEmail(technician.email());
       updatedTechnician.setShift(technician.shift());
       this.technicianRepository.save(updatedTechnician);

       return new TechnicianOutputDTO(updatedTechnician);
    }
}
