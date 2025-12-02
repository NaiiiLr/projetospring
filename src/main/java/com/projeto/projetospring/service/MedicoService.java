package com.projeto.projetospring.service;

import com.projeto.projetospring.dto.medico.MedicoRequestDTO;
import com.projeto.projetospring.dto.medico.MedicoResponseDTO;
import com.projeto.projetospring.entity.Medico;
import com.projeto.projetospring.mapper.MedicoMapper;
import com.projeto.projetospring.repository.MedicoRepository;
import com.projeto.projetospring.service.exceptions.ObjetoNaoEncontradoException;
import com.projeto.projetospring.service.exceptions.ViolacaoIntegridadeDadosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private MedicoMapper mapper;

    public Medico findById(Integer id) {
        Optional<Medico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Médico não encontrado! Id: " + id));
    }

    public List<MedicoResponseDTO> findAll() {
        return mapper.toResponseDtoList(repository.findAll());
    }

    public MedicoResponseDTO create(MedicoRequestDTO objDTO) {
        validaPorCpfEEmail(objDTO, null);
        Medico newObj = mapper.toEntity(objDTO);
        return mapper.toResponseDto(repository.save(newObj));
    }

    public MedicoResponseDTO update(Integer id, MedicoRequestDTO objDTO) {
        findById(id);
        validaPorCpfEEmail(objDTO, id);
        Medico oldObj = mapper.toEntity(objDTO);
        oldObj.setId(id);
        return mapper.toResponseDto(repository.save(oldObj));
    }

    @Transactional
    public void delete(Integer id) {
        Medico obj = findById(id);
        if (!obj.getConsultas().isEmpty()) {
            throw new ViolacaoIntegridadeDadosException("Médico possui consultas agendadas e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private void validaPorCpfEEmail(MedicoRequestDTO objDTO, Integer idParaIgnorar) {
        Optional<Medico> obj = repository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && !obj.get().getId().equals(idParaIgnorar)) {
            throw new ViolacaoIntegridadeDadosException("CPF já cadastrado no sistema!");
        }

        obj = repository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && !obj.get().getId().equals(idParaIgnorar)) {
            throw new ViolacaoIntegridadeDadosException("E-mail já cadastrado no sistema!");
        }
    }
}