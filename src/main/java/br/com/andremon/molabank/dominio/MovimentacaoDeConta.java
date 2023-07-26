package br.com.andremon.molabank.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class MovimentacaoDeConta {

    @JsonProperty
    private ContaCorrente conta;

    @JsonProperty
    private BigDecimal valor;

    @JsonProperty
    private Operacao operacao;
}
