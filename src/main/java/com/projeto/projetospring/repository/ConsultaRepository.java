package com.projeto.projetospring.repository;

import com.projeto.projetospring.entity.Consulta;
import com.projeto.projetospring.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

    List<Consulta> findAllByStatus(Status status);

    @Query("SELECT c FROM Consulta c WHERE c.medico.id = :medicoId")
    List<Consulta> findAllByMedico(@Param("medicoId") Integer medicoId);

    @Query("SELECT c FROM Consulta c WHERE c.paciente.id = :pacienteId")
    List<Consulta> findAllByPaciente(@Param("pacienteId") Integer pacienteId);
}