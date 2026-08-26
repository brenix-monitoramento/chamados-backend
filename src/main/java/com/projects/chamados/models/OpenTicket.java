package com.projects.chamados.models;

import com.projects.chamados.enums.OpenTicketStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "open_ticket")
public class OpenTicket {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "id_chamado", unique = true)
    private String idChamado;
    private OpenTicketStatus status;
    private String incident;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "end_time")
    private LocalTime endTime;
    private String observations;
    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Equipment equipment;

    public OpenTicket(){

    }

    public OpenTicket(UUID id, String idChamado, OpenTicketStatus status, String incident, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, String observations, Technician technician, Equipment equipment) {
        this.id = id;
        this.idChamado = idChamado;
        this.status = status;
        this.incident = incident;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.observations = observations;
        this.technician = technician;
        this.equipment = equipment;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setIdChamado(String idChamado) {
        this.idChamado = idChamado;
    }

    public void setStatus(OpenTicketStatus status) {
        this.status = status;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setTechnician(Technician technician){
        this.technician = technician;
    }

    public void setEquipment(Equipment equipment){
        this.equipment = equipment;
    }

    public UUID getId() {
        return id;
    }

    public String getIdChamado() {
        return idChamado;
    }

    public OpenTicketStatus getStatus() {
        return status;
    }

    public String getIncident() {
        return incident;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getObservations() {
        return observations;
    }

    public Technician getTechnician(){
        return this.technician;
    }

    public Equipment getEquipment(){
        return this.equipment;
    }
}
