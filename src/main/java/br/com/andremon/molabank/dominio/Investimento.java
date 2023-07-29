package br.com.andremon.molabank.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Investimento {

    @JsonProperty
    private String nome;

    @JsonProperty
    private BigDecimal rendimento;

    public Investimento(String nome, BigDecimal rendimento) {
        this.nome = nome;
        this.rendimento = rendimento;
    }

    public BigDecimal getRendimento() {
        return rendimento;
    }

    public String getNome() {
        return nome;
    }
}
