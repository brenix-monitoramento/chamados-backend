package com.projects.chamados.models;

import com.projects.chamados.enums.EquipmentType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="equipment")
public class Equipment {
    @Id
    @GeneratedValue
    private UUID id;
    private String location;
    @Column(name="id_sefit")
    private String idSefit;
    private EquipmentType type;
    @Column(name="serial_number")
    private String serialNumber;
    @ManyToOne
    @JoinColumn(name="technician_id")
    private Technician technician;
    @OneToMany(mappedBy = "equipment")
    private List<OpenTicket> openTickets = new ArrayList<>();

    public Equipment(){}

    public Equipment(UUID id, String location, String idSefit, EquipmentType type, String serialNumber, Technician technician) {
        this.id = id;
        this.location = location;
        this.idSefit = idSefit;
        this.type = type;
        this.serialNumber = serialNumber;
        this.technician = technician;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIdSefit() {
        return idSefit;
    }

    public void setIdSefit(String idSefit) {
        this.idSefit = idSefit;
    }

    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Technician getTechnician(){
        return this.technician;
    }

    public void setTechnician(Technician technician){
        this.technician = technician;
    }

    public List<OpenTicket> getOpenTickets(){return this.openTickets;}
}
