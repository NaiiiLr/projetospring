package com.projeto.projetospring.service;

import com.projeto.projetospring.dto.paciente.PacienteRequestDTO;
import com.projeto.projetospring.dto.paciente.PacienteResponseDTO;
import com.projeto.projetospring.entity.Paciente;
import com.projeto.projetospring.mapper.PacienteMapper;
import com.projeto.projetospring.repository.PacienteRepository;
import com.projeto.projetospring.service.exceptions.ObjetoNaoEncontradoException;
import com.projeto.projetospring.service.exceptions.ViolacaoIntegridadeDadosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private PacienteMapper mapper;

    public Paciente findById(Integer id) {
        Optional<Paciente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Paciente não encontrado! Id: " + id));
    }

    public List<PacienteResponseDTO> findAll() {
        return mapper.toResponseDtoList(repository.findAll());
    }

    public PacienteResponseDTO create(PacienteRequestDTO objDTO) {
        validaPorCpfEEmail(objDTO, null);
        Paciente newObj = mapper.toEntity(objDTO);
        return mapper.toResponseDto(repository.save(newObj));
    }

    public PacienteResponseDTO update(Integer id, PacienteRequestDTO objDTO) {
        findById(id);
        validaPorCpfEEmail(objDTO, id);
        Paciente oldObj = mapper.toEntity(objDTO);
        oldObj.setId(id);
        return mapper.toResponseDto(repository.save(oldObj));
    }

    @Transactional
    public void delete(Integer id) {
        Paciente obj = findById(id);
        if (!obj.getConsultas().isEmpty()) {
            throw new ViolacaoIntegridadeDadosException("Paciente possui consultas agendadas e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private void validaPorCpfEEmail(PacienteRequestDTO objDTO, Integer idParaIgnorar) {
        Optional<Paciente> obj = repository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && !obj.get().getId().equals(idParaIgnorar)) {
            throw new ViolacaoIntegridadeDadosException("CPF já cadastrado no sistema!");
        }

        obj = repository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && !obj.get().getId().equals(idParaIgnorar)) {
            throw new ViolacaoIntegridadeDadosException("E-mail já cadastrado no sistema!");
        }
    }
}