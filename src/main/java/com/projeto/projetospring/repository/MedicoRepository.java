package com.projeto.projetospring.repository;

import com.projeto.projetospring.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    Optional<Medico> findByCpf(String cpf);

    Optional<Medico> findByEmail(String email);
}