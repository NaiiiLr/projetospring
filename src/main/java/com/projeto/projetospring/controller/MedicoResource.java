package com.projeto.projetospring.controller;

import com.projeto.projetospring.dto.medico.MedicoRequestDTO;
import com.projeto.projetospring.dto.medico.MedicoResponseDTO;
import com.projeto.projetospring.entity.Medico;
import com.projeto.projetospring.mapper.MedicoMapper;
import com.projeto.projetospring.service.MedicoService;
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
@RequestMapping(value = "/medicos")
@Tag(name = "Médicos", description = "Gerenciamento de médicos")
public class MedicoResource {

    @Autowired
    private MedicoService service;

    @Autowired
    private MedicoMapper mapper;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um médico pelo ID")
    public ResponseEntity<MedicoResponseDTO> findById(@PathVariable(name = "id") Integer id) {
        Medico obj = service.findById(id);
        return ResponseEntity.ok().body(mapper.toResponseDto(obj));
    }

    @GetMapping
    @Operation(summary = "Lista todos os médicos")
    public ResponseEntity<List<MedicoResponseDTO>> findAll() {
        List<MedicoResponseDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo médico")
    public ResponseEntity<MedicoResponseDTO> create(@Valid @RequestBody MedicoRequestDTO objDTO) {
        MedicoResponseDTO newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza os dados de um médico")
    public ResponseEntity<MedicoResponseDTO> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody MedicoRequestDTO objDTO) {
        MedicoResponseDTO obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove um médico pelo ID")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}