package br.com.andremon.molabank.controller;

import br.com.andremon.molabank.dominio.ContaCorrente;
import br.com.andremon.molabank.dominio.Correntista;
import br.com.andremon.molabank.dominio.MovimentacaoDeConta;
import br.com.andremon.molabank.repositorios.RepositorioContasCorrente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private final RepositorioContasCorrente repositorioContasCorrente;

    public ContaController(RepositorioContasCorrente repositorioContasCorrente) {
        this.repositorioContasCorrente = repositorioContasCorrente;
    }

    @GetMapping
    public String consultarSaldo(
            @RequestParam(name = "banco") String banco,
            @RequestParam(name = "agencia") String agencia,
            @RequestParam(name = "numeroConta") String numeroConta) {

        ContaCorrente contaCorrente = repositorioContasCorrente
                .buscar(banco, agencia, numeroConta)
                .orElse(new ContaCorrente());

        return String.format("Banco: %s, Agência: %s, Número: %s, Saldo: %s"
                , banco, agencia, numeroConta, contaCorrente.lerSaldo());
    }

    @PostMapping
    public ResponseEntity<ContaCorrente> criarNovaConta(@RequestBody Correntista correntista) {
        String banco = "33";
        String agencia = "4001";
        String numeroConta = Integer.toString(new Random().nextInt(Integer.MAX_VALUE));
        ContaCorrente conta = new ContaCorrente(banco, agencia, numeroConta, correntista);
        repositorioContasCorrente.salvar(conta);

        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    @DeleteMapping
    public ResponseEntity<String> fecharConta(@RequestBody ContaCorrente conta) {
        Optional<ContaCorrente> opContaCorrente =
                repositorioContasCorrente
                        .buscar(conta.getBanco(), conta.getAgencia(), conta.getNumeroConta());

        if (opContaCorrente.isEmpty()) {
            return ResponseEntity.badRequest().body("Conta corrente não encontrada");
        } else {
            repositorioContasCorrente.fechar(conta);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Conta Fechada");
        }
    }

    @PutMapping
    public ResponseEntity<String> movimentarConta(
            @RequestBody MovimentacaoDeConta movimentacaoDeConta) {

        Optional<ContaCorrente> opContaCorrente =
                repositorioContasCorrente.buscar(movimentacaoDeConta.getBanco()
                        , movimentacaoDeConta.getAgencia()
                        , movimentacaoDeConta.getNumeroConta());
        if (opContaCorrente.isEmpty()) {
            return ResponseEntity.badRequest().body("Conta Corrente não existe");
        } else {
            ContaCorrente contaCorrente = opContaCorrente.get();
            movimentacaoDeConta.executarEm(contaCorrente);
            repositorioContasCorrente.salvar(contaCorrente);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Operação realizada com sucesso!");
        }

    }
}
