package com.projeto.projetospring.mapper;

import com.projeto.projetospring.dto.paciente.PacienteRequestDTO;
import com.projeto.projetospring.dto.paciente.PacienteResponseDTO;
import com.projeto.projetospring.entity.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

    Paciente toEntity(PacienteRequestDTO dto);

    @Mapping(source = "perfil.descricao", target = "perfil")
    PacienteResponseDTO toResponseDto(Paciente entity);

    List<PacienteResponseDTO> toResponseDtoList(List<Paciente> entityList);
}