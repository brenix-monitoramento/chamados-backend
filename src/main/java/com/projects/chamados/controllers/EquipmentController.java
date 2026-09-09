package com.projects.chamados.controllers;

import com.projects.chamados.dtos.inputs.EquipmentInputDTO;
import com.projects.chamados.dtos.outputs.EquipmentListOutputDTO;
import com.projects.chamados.dtos.outputs.EquipmentOutputDTO;
import com.projects.chamados.services.EquipmentService;
import com.projects.chamados.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value="/equipments")
@Tag(name = "Equipamentos")
@SecurityRequirement(name = Constants.SECURITY_SCHEME_NAME)
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @Operation(description = "Lista todos os equipamentos ou equipamento com base na pesquisa.")
    @ApiResponse(responseCode = "200")
    @GetMapping
    public ResponseEntity<EquipmentListOutputDTO> getAllBySearch(
            @RequestParam(required = true) String searchTerm
    ){
        var equipments = this.equipmentService.listAllBySearch(searchTerm);

        return ResponseEntity.ok().body(equipments);
    }

    @Operation(description = "Cadastra equipamento.")
    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<EquipmentOutputDTO> post(@RequestBody @Valid EquipmentInputDTO equipment){
        var createdEquipment = this.equipmentService.create(equipment);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdEquipment);
    }

    @Operation(description = "Atualiza equipamento.")
    @ApiResponse(responseCode = "200")
    @PutMapping(value="/{equipmentId}")
    public ResponseEntity<EquipmentOutputDTO> put(@PathVariable UUID equipmentId, @RequestBody @Valid EquipmentInputDTO equipment){
        var updatedEquipment = this.equipmentService.updateById(equipmentId, equipment);

        return ResponseEntity.ok().body(updatedEquipment);
    }

    @Operation(description = "Exclui equipamento.")
    @ApiResponse(responseCode = "204")
    @DeleteMapping(value="/{equipmentId}")
    public ResponseEntity<Void> delete(@PathVariable UUID equipmentId){
        this.equipmentService.deleteById(equipmentId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
