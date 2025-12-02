package com.projeto.projetospring.controller;

import com.projeto.projetospring.dto.receita.ReceitaRequestDTO;
import com.projeto.projetospring.dto.receita.ReceitaResponseDTO;
import com.projeto.projetospring.entity.Receita;
import com.projeto.projetospring.mapper.ReceitaMapper;
import com.projeto.projetospring.service.ReceitaService;
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
@RequestMapping(value = "/receitas")
@Tag(name = "Receitas", description = "Emissão de receitas médicas")
public class ReceitaResource {

    @Autowired
    private ReceitaService service;

    @Autowired
    private ReceitaMapper mapper;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca uma receita pelo ID")
    public ResponseEntity<ReceitaResponseDTO> findById(@PathVariable(name = "id") Integer id) {
        Receita obj = service.findById(id);
        return ResponseEntity.ok().body(mapper.toResponseDto(obj));
    }

    @GetMapping
    @Operation(summary = "Lista todas as receitas")
    public ResponseEntity<List<ReceitaResponseDTO>> findAll() {
        List<ReceitaResponseDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/consulta/{consultaId}")
    @Operation(summary = "Lista todas as receitas de uma consulta específica")
    public ResponseEntity<List<ReceitaResponseDTO>> findByConsulta(@PathVariable(name = "consultaId") Integer consultaId) {
        List<ReceitaResponseDTO> list = service.findByConsulta(consultaId);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    @Operation(summary = "Emite uma nova receita para uma consulta")
    public ResponseEntity<ReceitaResponseDTO> create(@Valid @RequestBody ReceitaRequestDTO objDTO) {
        ReceitaResponseDTO newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza os dados de uma receita")
    public ResponseEntity<ReceitaResponseDTO> update(@PathVariable(name = "id") Integer id, @Valid @RequestBody ReceitaRequestDTO objDTO) {
        ReceitaResponseDTO obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove uma receita pelo ID")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}