package com.projeto.projetospring.mapper;

import com.projeto.projetospring.dto.consulta.ConsultaRequestDTO;
import com.projeto.projetospring.dto.consulta.ConsultaResponseDTO;
import com.projeto.projetospring.entity.Consulta;
import com.projeto.projetospring.entity.enums.Prioridade;
import com.projeto.projetospring.entity.enums.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConsultaMapper {
    ConsultaMapper INSTANCE = Mappers.getMapper(ConsultaMapper.class);

    @Mapping(source = "medico", target = "medico.id")
    @Mapping(source = "paciente", target = "paciente.id")
    @Mapping(target = "prioridade", expression = "java(mapPrioridade(dto.getPrioridade()))")
    @Mapping(target = "status", expression = "java(mapStatus(dto.getStatus()))")
    Consulta toEntity(ConsultaRequestDTO dto);

    @Mapping(source = "prioridade.descricao", target = "prioridade")
    @Mapping(source = "status.descricao", target = "status")
    @Mapping(source = "medico.id", target = "medicoId")
    @Mapping(source = "medico.nome", target = "nomeMedico")
    @Mapping(source = "paciente.id", target = "pacienteId")
    @Mapping(source = "paciente.nome", target = "nomePaciente")
    ConsultaResponseDTO toResponseDto(Consulta entity);

    List<ConsultaResponseDTO> toResponseDtoList(List<Consulta> entityList);

    default Prioridade mapPrioridade(Integer codigo) {
        return Prioridade.toEnum(codigo);
    }

    default Status mapStatus(Integer codigo) {
        return Status.toEnum(codigo);
    }
}