package com.projects.chamados.config;

import com.projects.chamados.enums.EquipmentType;
import com.projects.chamados.enums.OpenTicketStatus;
import com.projects.chamados.enums.TechnicianShift;
import com.projects.chamados.models.Equipment;
import com.projects.chamados.models.OpenTicket;
import com.projects.chamados.models.Technician;
import com.projects.chamados.repositories.EquipmentRepository;
import com.projects.chamados.repositories.OpenTicketRepository;
import com.projects.chamados.repositories.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private TechnicianRepository technicianRepository;
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    OpenTicketRepository openTicketRepository;

    @Override
    public void run(String... args){
        // instâncias de técnicos
        var John = new Technician(null, "John Lorem Ipsum", "john@mail.com", TechnicianShift.DAYTIME, "123password.");
        var Marie = new Technician(null, "Marie Lorem Ipsum", "marie@mail.com", TechnicianShift.NIGHTTIME, "123password.");
        var Andrew = new Technician(null, "Andrew Lorem Ipsum", "andrew@mail.com", TechnicianShift.DAYTIME, "123password.");
        var Paul = new Technician(null, "Paul Lorem Ipsum", "paul@mail.com", TechnicianShift.NIGHTTIME, "123password.");

        // instâncias de equipamentos
        var equipmentOne = new Equipment(null, "BR 070 DF", "34324", EquipmentType.WIM, "079", John);
        var equipmentTwo = new Equipment(null, "BR 251 sentido GO", "657567657", EquipmentType.WIM, "098", Marie);
        var equipmentThree = new Equipment(null, "Avenida Limeira Principal", "9098098", EquipmentType.OCR, "1129", Marie);

        // instâncias de chamados
        var openTicketOne = new OpenTicket(null, "1", OpenTicketStatus.COMPLETED, "Equipamento off-line", LocalDate.now(), LocalTime.now(), LocalDate.now().plusDays(2), LocalTime.now(), null, John, equipmentOne);
        var openTicketTwo = new OpenTicket(null, "22", OpenTicketStatus.IN_PROGRESS, "Equipamento vandalizado", LocalDate.now(), LocalTime.now(), null, null, "Vandalizado.", Marie, equipmentTwo);
        var openTicketThree = new OpenTicket(null, "67", OpenTicketStatus.COMPLETED, "Equipamento off-line", LocalDate.now(), LocalTime.now(), LocalDate.now().plusDays(2), LocalTime.now(), null, Paul, equipmentThree);

        // salvamento de entidades
        this.technicianRepository.saveAll(Arrays.asList(John, Marie, Andrew, Paul));
        this.equipmentRepository.saveAll(Arrays.asList(equipmentOne, equipmentTwo, equipmentThree));
        this.openTicketRepository.saveAll(Arrays.asList(openTicketOne, openTicketTwo, openTicketThree));
    }
}
