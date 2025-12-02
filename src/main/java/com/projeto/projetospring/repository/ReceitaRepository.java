package com.projeto.projetospring.repository;

import com.projeto.projetospring.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer> {

    List<Receita> findAllByConsultaId(Integer consultaId);
}