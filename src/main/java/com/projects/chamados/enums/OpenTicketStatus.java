package com.projects.chamados.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OpenTicketStatus {
    IN_PROGRESS("Em andamento"),
    COMPLETED("Finalizado");

    private String description;

    private OpenTicketStatus(String description){
        this.description = description;
    }

    @JsonValue
    public String getDescription(){return this.description;}
}
