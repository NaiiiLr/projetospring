package com.projeto.projetospring.entity.enums;

public enum Status {

    ABERTO(0, "Aberto"),
    ANDAMENTO(1, "Em Andamento"),
    ENCERRADO(2, "Encerrado");

    private Integer codigo;
    private String descricao;

    Status(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Status s : Status.values()) {
            if (cod.equals(s.getCodigo())) {
                return s;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + cod);
    }
}