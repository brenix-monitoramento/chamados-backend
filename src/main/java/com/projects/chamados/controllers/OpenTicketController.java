package com.projects.chamados.controllers;

import com.projects.chamados.dtos.outputs.OpenTicketOutputDTO;
import com.projects.chamados.services.OpenTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
