package com.projeto.projetospring.mapper;

import com.projeto.projetospring.dto.medico.MedicoRequestDTO;
import com.projeto.projetospring.dto.medico.MedicoResponseDTO;
import com.projeto.projetospring.entity.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicoMapper {
    MedicoMapper INSTANCE = Mappers.getMapper(MedicoMapper.class);

    Medico toEntity(MedicoRequestDTO dto);

    @Mapping(source = "perfil.descricao", target = "perfil")
    MedicoResponseDTO toResponseDto(Medico entity);

    List<MedicoResponseDTO> toResponseDtoList(List<Medico> entityList);
}