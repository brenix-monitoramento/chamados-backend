package com.projects.chamados.controllers;

import com.projects.chamados.dtos.inputs.OpenTicketInputDTO;
import com.projects.chamados.dtos.outputs.OpenTicketOutputDTO;
import com.projects.chamados.services.OpenTicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/open-tickets")
public class OpenTicketController {
    @Autowired
    private OpenTicketService openTicketService;

    @GetMapping
    public ResponseEntity<List<OpenTicketOutputDTO>> getAllBySearch(
            @RequestParam(required = true) String searchTerm
    ){
        var openTickets = this.openTicketService.listAllBySearch(searchTerm);

        return ResponseEntity.ok().body(openTickets);
    }

    @PostMapping
    public ResponseEntity<OpenTicketOutputDTO> post(@RequestBody @Valid OpenTicketInputDTO openTicket){
        var createdOpenTicket = this.openTicketService.create(openTicket);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdOpenTicket);
    }

    @DeleteMapping(value = "/{openTicketId}")
    public ResponseEntity<Void> delete(@PathVariable UUID openTicketId){
        this.openTicketService.deleteById(openTicketId);

        return ResponseEntity.noContent().build();
    }
}
