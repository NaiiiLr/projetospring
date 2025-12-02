package com.projeto.projetospring.service;

import com.projeto.projetospring.dto.consulta.ConsultaRequestDTO;
import com.projeto.projetospring.dto.consulta.ConsultaResponseDTO;
import com.projeto.projetospring.entity.Consulta;
import com.projeto.projetospring.entity.Medico;
import com.projeto.projetospring.entity.Paciente;
import com.projeto.projetospring.entity.enums.Prioridade;
import com.projeto.projetospring.entity.enums.Status;
import com.projeto.projetospring.mapper.ConsultaMapper;
import com.projeto.projetospring.repository.ConsultaRepository;
import com.projeto.projetospring.service.exceptions.ObjetoNaoEncontradoException;
import com.projeto.projetospring.service.exceptions.ViolacaoIntegridadeDadosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private ConsultaMapper mapper;

    public Consulta findById(Integer id) {
        Optional<Consulta> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Consulta não encontrada! ID: " + id));
    }

    public List<ConsultaResponseDTO> findAll() {
        return mapper.toResponseDtoList(repository.findAll());
    }

    public ConsultaResponseDTO create(ConsultaRequestDTO objDTO) {
        return mapper.toResponseDto(repository.save(novaConsulta(objDTO, null)));
    }

    public ConsultaResponseDTO update(Integer id, ConsultaRequestDTO objDTO) {
        Consulta consulta = findById(id);
        Consulta newConsulta = novaConsulta(objDTO, id);
        newConsulta.setId(id);
        newConsulta.setDataAbertura(consulta.getDataAbertura());
        return mapper.toResponseDto(repository.save(newConsulta));
    }

    @Transactional
    public void delete(Integer id) {
        Consulta obj = findById(id);

        if (obj.getStatus() != Status.ENCERRADO) {
            throw new ViolacaoIntegridadeDadosException("Esta consulta não pode ser cancelada/deletada pois não está encerrada!");
        }

        if (!obj.getReceitas().isEmpty()) {
            throw new ViolacaoIntegridadeDadosException("Esta consulta possui receitas emitidas e não pode ser deletada! Remova as receitas antes.");
        }

        repository.deleteById(id);
    }

    private Consulta novaConsulta(ConsultaRequestDTO obj, Integer id) {
        Medico medico = medicoService.findById(obj.getMedico());
        Paciente paciente = pacienteService.findById(obj.getPaciente());

        Consulta consulta = new Consulta();
        if(id != null) {
            consulta.setId(id);
        }

        if(obj.getStatus().equals(2)) {
            consulta.setDataFechamento(LocalDate.now());
        }

        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        consulta.setStatus(Status.toEnum(obj.getStatus()));
        consulta.setTitulo(obj.getTitulo());
        consulta.setObservacoes(obj.getObservacoes());
        return consulta;
    }
}