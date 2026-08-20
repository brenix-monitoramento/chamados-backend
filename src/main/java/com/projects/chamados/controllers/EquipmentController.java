package com.projects.chamados.controllers;

import com.projects.chamados.dtos.inputs.EquipmentInputDTO;
import com.projects.chamados.dtos.outputs.EquipmentOutputDTO;
import com.projects.chamados.services.EquipmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value="/equipments")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<List<EquipmentOutputDTO>> getAll(){
        var equipments = this.equipmentService.listAll();

        return ResponseEntity.ok().body(equipments);
    }

    @PostMapping
    public ResponseEntity<EquipmentOutputDTO> post(@RequestBody @Valid EquipmentInputDTO equipment){
        var createdEquipment = this.equipmentService.create(equipment);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdEquipment);
    }

    @PutMapping(value="/{equipmentId}")
    public ResponseEntity<EquipmentOutputDTO> put(@PathVariable UUID equipmentId, @RequestBody @Valid EquipmentInputDTO equipment){
        var updatedEquipment = this.equipmentService.updateById(equipmentId, equipment);

        return ResponseEntity.ok().body(updatedEquipment);
    }

    @DeleteMapping(value="/{equipmentId}")
    public ResponseEntity<Void> delete(@PathVariable UUID equipmentId){
        this.equipmentService.deleteById(equipmentId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
