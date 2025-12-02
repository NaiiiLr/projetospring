package com.projeto.projetospring.service;

import com.projeto.projetospring.dto.receita.ReceitaRequestDTO;
import com.projeto.projetospring.dto.receita.ReceitaResponseDTO;
import com.projeto.projetospring.entity.Consulta;
import com.projeto.projetospring.entity.Receita;
import com.projeto.projetospring.mapper.ReceitaMapper;
import com.projeto.projetospring.repository.ReceitaRepository;
import com.projeto.projetospring.service.exceptions.ObjetoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository repository;

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ReceitaMapper mapper;

    public Receita findById(Integer id) {
        Optional<Receita> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Receita não encontrada! ID: " + id));
    }

    public List<ReceitaResponseDTO> findAll() {
        return mapper.toResponseDtoList(repository.findAll());
    }

    public List<ReceitaResponseDTO> findByConsulta(Integer consultaId) {
        return mapper.toResponseDtoList(repository.findAllByConsultaId(consultaId));
    }

    public ReceitaResponseDTO create(ReceitaRequestDTO objDTO) {
        Consulta consulta = consultaService.findById(objDTO.getConsulta());

        Receita receita = mapper.toEntity(objDTO);
        receita.setConsulta(consulta);

        return mapper.toResponseDto(repository.save(receita));
    }

    public ReceitaResponseDTO update(Integer id, ReceitaRequestDTO objDTO) {

        Receita receitaAntiga = findById(id);

        Consulta consulta = consultaService.findById(objDTO.getConsulta());

        Receita novaReceita = mapper.toEntity(objDTO);
        novaReceita.setId(id);
        novaReceita.setDataEmissao(receitaAntiga.getDataEmissao());
        novaReceita.setConsulta(consulta);

        return mapper.toResponseDto(repository.save(novaReceita));
    }

    @Transactional
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
}