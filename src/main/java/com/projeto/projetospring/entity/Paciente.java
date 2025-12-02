package com.projeto.projetospring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.projetospring.entity.enums.Perfil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Paciente extends Usuario {
    @Serial
    private static final long serialVersionUID = 1L;

    private String numCarteirinhaConvenio;

    @Column(columnDefinition = "TEXT")
    private String historicoAlergias;

    @JsonIgnore
    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas = new ArrayList<>();

    public Paciente() {
        super();
        setPerfil(Perfil.PACIENTE);
    }

    public Paciente(Integer id, String nome, String cpf, String email, String senha, String numCarteirinhaConvenio, String historicoAlergias) {
        super(id, nome, cpf, email, senha);
        this.numCarteirinhaConvenio = numCarteirinhaConvenio;
        this.historicoAlergias = historicoAlergias;
        setPerfil(Perfil.PACIENTE);
    }

    public String getNumCarteirinhaConvenio() {
        return numCarteirinhaConvenio;
    }

    public void setNumCarteirinhaConvenio(String numCarteirinhaConvenio) {
        this.numCarteirinhaConvenio = numCarteirinhaConvenio;
    }

    public String getHistoricoAlergias() {
        return historicoAlergias;
    }

    public void setHistoricoAlergias(String historicoAlergias) {
        this.historicoAlergias = historicoAlergias;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}