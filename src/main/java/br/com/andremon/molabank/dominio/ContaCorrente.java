package br.com.andremon.molabank.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class ContaCorrente {
    @JsonProperty
    private String banco;
    @JsonProperty
    private String agencia;
    @JsonProperty
    private String numeroConta;

    @JsonProperty
    private BigDecimal saldo;

    @JsonIgnore
    private Correntista correntista;

    public ContaCorrente(String banco, String agencia, String numeroConta, Correntista correntista) {
        this();
        this.agencia = agencia;
        this.banco = banco;
        this.numeroConta = numeroConta;
        this.correntista = correntista;
    }

    public ContaCorrente() {
        this.saldo = BigDecimal.ZERO;
    }

    public boolean identificaPor(String banco, String agencia, String numeroConta) {
        return this.banco.equals(banco)
                && this.agencia.equals(agencia)
                && this.numeroConta.equals(numeroConta);
    }

    public BigDecimal lerSaldo() {
        return this.saldo;
    }

    public void executar(Operacao operacao, BigDecimal valor) {
        this.saldo = operacao.executar(saldo, valor);
    }

    public int obterNumeroConta() {
        return Integer.parseInt(numeroConta);
    }

    public String getBanco() {
        return this.banco;
    }

    public String getAgencia() {
        return this.agencia;
    }

    public String getNumeroConta() {
        return this.numeroConta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContaCorrente that = (ContaCorrente) o;
        return Objects.equals(banco, that.banco) && Objects.equals(agencia, that.agencia) && Objects.equals(numeroConta, that.numeroConta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banco, agencia, numeroConta);
    }
}
