package com.projeto.projetospring.dto.consulta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class ConsultaRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O campo TÍTULO é obrigatório.")
    private String titulo;

    @NotBlank(message = "O campo OBSERVAÇÕES é obrigatório.")
    private String observacoes;

    @NotNull(message = "O campo PRIORIDADE é obrigatório.")
    private Integer prioridade;

    @NotNull(message = "O campo STATUS é obrigatório.")
    private Integer status;

    @NotNull(message = "O campo MÉDICO é obrigatório.")
    private Integer medico;

    @NotNull(message = "O campo PACIENTE é obrigatório.")
    private Integer paciente;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public Integer getPrioridade() { return prioridade; }
    public void setPrioridade(Integer prioridade) { this.prioridade = prioridade; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Integer getMedico() { return medico; }
    public void setMedico(Integer medico) { this.medico = medico; }
    public Integer getPaciente() { return paciente; }
    public void setPaciente(Integer paciente) { this.paciente = paciente; }
}