package com.projeto.projetospring.dto.receita;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class ReceitaRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "A DESCRIÇÃO DOS MEDICAMENTOS é obrigatória.")
    private String descricaoMedicamentos;

    @NotNull(message = "O ID da CONSULTA é obrigatório.")
    private Integer consulta;

    public String getDescricaoMedicamentos() {
        return descricaoMedicamentos;
    }

    public void setDescricaoMedicamentos(String descricaoMedicamentos) {
        this.descricaoMedicamentos = descricaoMedicamentos;
    }

    public Integer getConsulta() {
        return consulta;
    }

    public void setConsulta(Integer consulta) {
        this.consulta = consulta;
    }
}