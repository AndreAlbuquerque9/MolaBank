package br.com.andremon.molabank.repositorios;

import br.com.andremon.molabank.dominio.ContaCorrente;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class RepositorioContasCorrente {

    private Set<ContaCorrente> contas;

    public RepositorioContasCorrente() {
        contas = new HashSet<>();
    }
    public void salvar(ContaCorrente conta) {
        contas.add(conta);
    }

    public Optional<ContaCorrente> buscar(String banco, String agencia, String numeroConta) {
        return contas.stream()
                .filter(contaCorrente -> contaCorrente.identificaPor(banco, agencia, numeroConta))
                .findFirst();
    }

    public void fechar(ContaCorrente conta) {
        contas.remove(conta);
    }
}
