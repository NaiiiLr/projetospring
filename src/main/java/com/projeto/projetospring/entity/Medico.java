package com.projeto.projetospring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.projetospring.entity.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Medico extends Usuario {
    @Serial
    private static final long serialVersionUID = 1L;

    private String crm;
    private BigDecimal valorConsulta;

    @JsonIgnore
    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas = new ArrayList<>();

    public Medico() {
        super();
        setPerfil(Perfil.MEDICO);
    }

    public Medico(Integer id, String nome, String cpf, String email, String senha, String crm, BigDecimal valorConsulta) {
        super(id, nome, cpf, email, senha);
        this.crm = crm;
        this.valorConsulta = valorConsulta;
        setPerfil(Perfil.MEDICO);
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public BigDecimal getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(BigDecimal valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}