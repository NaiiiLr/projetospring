package com.projeto.projetospring.entity.enums.converters;

import com.projeto.projetospring.entity.enums.Prioridade;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PrioridadeConverter implements AttributeConverter<Prioridade, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Prioridade prioridade) {
        if (prioridade == null) {
            return null;
        }
        return prioridade.getCodigo();
    }

    @Override
    public Prioridade convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return Prioridade.toEnum(dbData);
    }
}