package com.projeto.projetospring.dto.paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

public class PacienteRequestDTO implements Serializable {
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

    private String numCarteirinhaConvenio;

    private String historicoAlergias;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getNumCarteirinhaConvenio() { return numCarteirinhaConvenio; }
    public void setNumCarteirinhaConvenio(String numCarteirinhaConvenio) { this.numCarteirinhaConvenio = numCarteirinhaConvenio; }
    public String getHistoricoAlergias() { return historicoAlergias; }
    public void setHistoricoAlergias(String historicoAlergias) { this.historicoAlergias = historicoAlergias; }
}