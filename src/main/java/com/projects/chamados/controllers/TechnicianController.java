package com.projects.chamados.controllers;

import com.projects.chamados.dtos.inputs.TechnicianInputDTO;
import com.projects.chamados.dtos.outputs.TechnicianOutputDTO;
import com.projects.chamados.services.TechnicianService;
import com.projects.chamados.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value="/technicians")
@Tag(name = "Técnicos")
@SecurityRequirement(name = Constants.SECURITY_SCHEME_NAME)
public class TechnicianController {
    @Autowired
    private TechnicianService technicianService;

    @Operation(description = "Lista todos os técnicos.")
    @ApiResponse(responseCode = "200")
    @GetMapping
    public ResponseEntity<List<TechnicianOutputDTO>> getAll(){
        var technicians = this.technicianService.listAll();

        return ResponseEntity.ok().body(technicians);
    }

    @Operation(description = "Atualiza técnico.")
    @ApiResponse(responseCode = "200")
    @PutMapping(value = "/{technicianId}")
    public ResponseEntity<TechnicianOutputDTO> put(@PathVariable UUID technicianId, @Valid @RequestBody TechnicianInputDTO technician){
        var updatedTechnician = this.technicianService.updateById(technicianId, technician);

        return ResponseEntity.ok().body(updatedTechnician);
    }
}
