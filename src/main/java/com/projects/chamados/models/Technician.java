package com.projects.chamados.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projects.chamados.enums.TechnicianShift;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="technician")
public class Technician {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String email;
    private TechnicianShift shift;
    private String password;
    @OneToMany(mappedBy = "technician")
    private List<Equipment> equipments = new ArrayList<>();
    @OneToMany(mappedBy = "technician")
    private List<OpenTicket> openTickets = new ArrayList<>();

    public Technician() {

    }

    public Technician(UUID id, String name, String email, TechnicianShift shift, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.shift = shift;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TechnicianShift getShift() {
        return this.shift;
    }

    public void setShift(TechnicianShift shift) {
        this.shift = shift;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Equipment> getEquipments() {
        return this.equipments;
    }

    public List<OpenTicket> getOpenTickets() {return this.openTickets;}
}