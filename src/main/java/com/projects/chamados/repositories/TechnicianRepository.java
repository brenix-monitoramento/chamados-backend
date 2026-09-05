package com.projects.chamados.repositories;

import com.projects.chamados.dtos.outputs.TechnicianOutputDTO;
import com.projects.chamados.models.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TechnicianRepository extends JpaRepository<Technician, UUID> {
   @Query("""
        SELECT new com.projects.chamados.dtos.outputs.TechnicianOutputDTO(
            technician.id,
            technician.name,
            technician.email,
            technician.shift
            )
         FROM Technician technician
           """)
    List<TechnicianOutputDTO> findAllWithoutPassword();

   Optional<Technician> findByEmail(String email);
}
