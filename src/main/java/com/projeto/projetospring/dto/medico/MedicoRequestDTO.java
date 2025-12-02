package com.projeto.projetospring.dto.medico;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.math.BigDecimal;

public class MedicoRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "O campo NOME é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "O campo CPF é obrigatório.")
    @CPF(message = "CPF inválido.")
    private String cpf;

    @NotBlank(message = "O campo EMAIL é obrigatório.")
    @Email(message = "Email inválido.")
    private String email;

    @NotBlank(message = "O campo SENHA é obrigatório.")
    @Size(min = 4, message = "A senha deve ter no mínimo 4 caracteres.")
    private String senha;

    @NotBlank(message = "O campo CRM é obrigatório.")
    private String crm;

    @NotNull(message = "O campo VALOR DA CONSULTA é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor da consulta deve ser positivo.")
    private BigDecimal valorConsulta;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }
    public BigDecimal getValorConsulta() { return valorConsulta; }
    public void setValorConsulta(BigDecimal valorConsulta) { this.valorConsulta = valorConsulta; }
}