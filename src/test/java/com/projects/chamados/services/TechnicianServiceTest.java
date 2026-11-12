package com.projects.chamados.services;

import com.projects.chamados.exceptions.NotFoundException;
import com.projects.chamados.repositories.TechnicianRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.projects.chamados.constants.TechnicianConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TechnicianServiceTest {
    @InjectMocks
    private TechnicianService technicianService;

    @Mock
    private TechnicianRepository technicianRepository;

    @Test
    public void findExistentTechnicianById(){
        when(this.technicianRepository.findById(TECHNICIAN_ID))
                .thenReturn(Optional.of(TECHNICIAN_ONE));

        var technician = this.technicianService.findIfExists(TECHNICIAN_ID);

        assertThat(technician).isEqualTo(TECHNICIAN_ONE);
    }

    @Test
    public void findInexistentTechnicianById(){
        when(this.technicianRepository.findById(TECHNICIAN_ID))
                .thenThrow(NotFoundException.class);

        assertThatThrownBy(() ->
                this.technicianService.findIfExists(TECHNICIAN_ID)
        ).isInstanceOf(NotFoundException.class);
    }

    @Test
    public void findExistentTechnicianByEmail(){
        String technicianEmail = TECHNICIAN_ONE.getEmail();

        when(this.technicianRepository.findByEmailIgnoreCase(technicianEmail))
                .thenReturn(Optional.of(TECHNICIAN_ONE));

        var technician = this.technicianService.findAuthenticated();

        assertThat(technician).isEqualTo(TECHNICIAN_ONE);
    }

    @Test
    public void findInexistentTechnicianByEmail(){
        String technicianEmail = TECHNICIAN_ONE.getEmail();

        when(this.technicianRepository.findByEmailIgnoreCase(technicianEmail))
                .thenThrow(NotFoundException.class);

        assertThatThrownBy(() ->
                this.technicianService.findAuthenticated()
        ).isInstanceOf(NotFoundException.class);
    }

    @Test
    public void listAllTechnician(){
        when(technicianRepository.findAllWithoutPassword()).thenReturn(TECHNICIAN_LIST_MOCK);

        var technicianList = this.technicianService.listAll();

        assertThat(technicianList).isEqualTo(TECHNICIAN_LIST_MOCK);
    }

   //fazer testes dos métodos updateById e loadUserByUsername

}
