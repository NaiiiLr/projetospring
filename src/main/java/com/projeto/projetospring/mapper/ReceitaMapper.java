package com.projeto.projetospring.mapper;

import com.projeto.projetospring.dto.receita.ReceitaRequestDTO;
import com.projeto.projetospring.dto.receita.ReceitaResponseDTO;
import com.projeto.projetospring.entity.Receita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReceitaMapper {
    ReceitaMapper INSTANCE = Mappers.getMapper(ReceitaMapper.class);

    @Mapping(source = "consulta", target = "consulta.id")
    Receita toEntity(ReceitaRequestDTO dto);

    @Mapping(source = "consulta.id", target = "consultaId")
    ReceitaResponseDTO toResponseDto(Receita entity);

    List<ReceitaResponseDTO> toResponseDtoList(List<Receita> entityList);
}