package com.projeto.projetospring.entity.enums.converters;

import com.projeto.projetospring.entity.enums.Perfil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PerfilConverter implements AttributeConverter<Perfil, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Perfil perfil) {
        if (perfil == null) {
            return null;
        }
        return perfil.getCodigo();
    }

    @Override
    public Perfil convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return Perfil.toEnum(dbData);
    }
}