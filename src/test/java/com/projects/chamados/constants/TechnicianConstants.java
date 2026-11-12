package com.projects.chamados.constants;

import com.projects.chamados.dtos.outputs.TechnicianOutputDTO;
import com.projects.chamados.enums.TechnicianShift;
import com.projects.chamados.models.Technician;

import java.util.List;
import java.util.UUID;

public class TechnicianConstants {
    public static Technician TECHNICIAN_ONE = new Technician(null, "John Lorem Ipsum", "john@mail.com", TechnicianShift.DAYTIME,  null);
    public static Technician TECHNICIAN_TWO = new Technician(null, "Marie Lorem Ipsum", "marie@mail.com", TechnicianShift.NIGHTTIME, null);
    public static UUID TECHNICIAN_ID = UUID.randomUUID();
    public static List<TechnicianOutputDTO> TECHNICIAN_LIST_MOCK = List.of(TECHNICIAN_ONE, TECHNICIAN_TWO)
            .stream()
            .map(technician -> new TechnicianOutputDTO(technician)).toList();
}
