package com.projeto.projetospring.controller;

import com.projeto.projetospring.dto.paciente.PacienteRequestDTO;
import com.projeto.projetospring.dto.paciente.PacienteResponseDTO;
import com.projeto.projetospring.entity.Paciente;
import com.projeto.projetospring.mapper.PacienteMapper;
import com.projeto.projetospring.service.PacienteService;
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
@RequestMapping(value = "/pacientes")
@Tag(name = "Pacientes", description = "Gerenciamento de pacientes")
public class PacienteResource {

    @Autowired
    private PacienteService service;

    @Autowired
    private PacienteMapper mapper;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um paciente pelo ID")
    public ResponseEntity<PacienteResponseDTO> findById(@PathVariable(name = "id") Integer id) {
        Paciente obj = service.findById(id);
        return ResponseEntity.ok().body(mapper.toResponseDto(obj));
    }

    @GetMapping
    @Operation(summary = "Lista todos os pacientes")
    public ResponseEntity<List<PacienteResponseDTO>> findAll() {
        List<PacienteResponseDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo paciente")
    public ResponseEntity<PacienteResponseDTO> create(@Valid @RequestBody PacienteRequestDTO objDTO) {
        PacienteResponseDTO newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza os dados de um paciente")
    public ResponseEntity<PacienteResponseDTO> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody PacienteRequestDTO objDTO) {
        PacienteResponseDTO obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove um paciente pelo ID")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}