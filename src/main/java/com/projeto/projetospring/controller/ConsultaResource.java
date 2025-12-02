package com.projeto.projetospring.controller;

import com.projeto.projetospring.dto.consulta.ConsultaRequestDTO;
import com.projeto.projetospring.dto.consulta.ConsultaResponseDTO;
import com.projeto.projetospring.entity.Consulta;
import com.projeto.projetospring.mapper.ConsultaMapper;
import com.projeto.projetospring.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/consultas")
@Tag(name = "Consultas", description = "Agendamento e gerenciamento de consultas")
public class ConsultaResource {

    @Autowired
    private ConsultaService service;

    @Autowired
    private ConsultaMapper mapper;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca uma consulta pelo ID")
    public ResponseEntity<ConsultaResponseDTO> findById(@PathVariable(name = "id") Integer id) {
        Consulta obj = service.findById(id);
        return ResponseEntity.ok().body(mapper.toResponseDto(obj));
    }

    @GetMapping
    @Operation(summary = "Lista todas as consultas")
    public ResponseEntity<List<ConsultaResponseDTO>> findAll() {
        List<ConsultaResponseDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    @Operation(summary = "Agenda uma nova consulta")
    public ResponseEntity<ConsultaResponseDTO> create(@Valid @RequestBody ConsultaRequestDTO objDTO) {
        ConsultaResponseDTO newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza os dados de uma consulta")
    public ResponseEntity<ConsultaResponseDTO> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody ConsultaRequestDTO objDTO) {
        ConsultaResponseDTO obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Cancela/Remove uma consulta pelo ID (Apenas se ENCERRADA)")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}