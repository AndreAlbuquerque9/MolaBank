package br.com.andremon.molabank.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContaCorrente {
    @JsonProperty
    String banco;
    @JsonProperty
    String agencia;
    @JsonProperty
    String numeroConta;

    public ContaCorrente(String banco, String agencia, String numeroConta) {
        this.agencia = agencia;
        this.banco = banco;
        this.numeroConta = numeroConta;
    }
}
