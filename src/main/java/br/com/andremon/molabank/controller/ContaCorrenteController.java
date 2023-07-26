package br.com.andremon.molabank.controller;

import br.com.andremon.molabank.dominio.ContaCorrente;
import br.com.andremon.molabank.dominio.Correntista;
import br.com.andremon.molabank.dominio.MovimentacaoDeConta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas")
public class ContaCorrenteController {

    @GetMapping
    public String consultarSaldo(
            @RequestParam(name = "banco") String banco,
            @RequestParam(name = "agencia") String agencia,
            @RequestParam(name = "numeroConta") String numeroConta) {
        return String.format("Banco: %s, Agência: %s, Número: %s, Saldo: R$200,00", banco, agencia, numeroConta);
    }

    @PostMapping
    public ResponseEntity<ContaCorrente> criarNovaConta(@RequestBody Correntista correntista) {
        ContaCorrente conta = new ContaCorrente("33", "4001", "18975546");
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    @DeleteMapping
    public String fecharConta(@RequestBody ContaCorrente conta) {
        return "Conta Fechada";
    }

    @PutMapping
    public ResponseEntity<MovimentacaoDeConta> movimentarConta(
            @RequestBody MovimentacaoDeConta movimentacaoDeConta) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
