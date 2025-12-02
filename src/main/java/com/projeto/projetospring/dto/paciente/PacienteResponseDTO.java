package com.projeto.projetospring.dto.paciente;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;

public class PacienteResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private String perfil;
    private String numCarteirinhaConvenio;
    private String historicoAlergias;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }
    public String getNumCarteirinhaConvenio() { return numCarteirinhaConvenio; }
    public void setNumCarteirinhaConvenio(String numCarteirinhaConvenio) { this.numCarteirinhaConvenio = numCarteirinhaConvenio; }
    public String getHistoricoAlergias() { return historicoAlergias; }
    public void setHistoricoAlergias(String historicoAlergias) { this.historicoAlergias = historicoAlergias; }
    public LocalDate getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }
}