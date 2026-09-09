package com.projects.chamados.controllers;

import com.projects.chamados.dtos.inputs.OpenTicketInputDTO;
import com.projects.chamados.dtos.outputs.OpenTicketOutputDTO;
import com.projects.chamados.services.OpenTicketService;
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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/open-tickets")
@Tag(name = "Chamados")
@SecurityRequirement(name = Constants.SECURITY_SCHEME_NAME)
public class OpenTicketController {
    @Autowired
    private OpenTicketService openTicketService;

    @Operation(description = "Lista todos os chamados ou chamado com base na pesquisa.")
    @ApiResponse(responseCode = "200")
    @GetMapping
    public ResponseEntity<List<OpenTicketOutputDTO>> getAllBySearch(
            @RequestParam(required = true) String searchTerm
    ){
        var openTickets = this.openTicketService.listAllBySearch(searchTerm);

        return ResponseEntity.ok().body(openTickets);
    }

    @Operation(description = "Cadastra novo chamado.")
    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<OpenTicketOutputDTO> post(@RequestBody @Valid OpenTicketInputDTO openTicket){
        var createdOpenTicket = this.openTicketService.create(openTicket);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdOpenTicket);
    }

    @Operation(description = "Atualiza chamado.")
    @ApiResponse(responseCode = "200")
    @PutMapping(value = "/{openTicketId}")
    public ResponseEntity<OpenTicketOutputDTO> put(@PathVariable UUID openTicketId, @RequestBody @Valid OpenTicketInputDTO openTicket){
        var updatedOpenTicket = this.openTicketService.updateById(openTicketId, openTicket);

        return ResponseEntity.ok().body(updatedOpenTicket);
    }

    @Operation(description = "Exclui chamado.")
    @ApiResponse(responseCode = "204")
    @DeleteMapping(value = "/{openTicketId}")
    public ResponseEntity<Void> delete(@PathVariable UUID openTicketId){
        this.openTicketService.deleteById(openTicketId);

        return ResponseEntity.noContent().build();
    }
}
