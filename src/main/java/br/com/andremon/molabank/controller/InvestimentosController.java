package br.com.andremon.molabank.controller;

import br.com.andremon.molabank.dominio.Investimento;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/investimentos")
public class InvestimentosController {

    Map<String, BigDecimal> mapInvestimentos = new HashMap<>();

    @PostMapping
    public ResponseEntity<Investimento> criarInvestimento(@RequestBody Investimento investimentoBody) {
        Investimento investimento = new Investimento(investimentoBody.getNome(), investimentoBody.getRendimento());
        mapInvestimentos.put(investimento.getNome(),investimento.getRendimento());
        return ResponseEntity.status(HttpStatus.CREATED).body(investimento);
    }

    @GetMapping
    public ResponseEntity<String> render(BigDecimal valorASerRendido, String nomeInvestimento) {
        BigDecimal getRendimentoMap = mapInvestimentos.get(nomeInvestimento);

        if(getRendimentoMap == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Investimento não encontrado. Tente novamente.");
        }

        BigDecimal rendimento;
        rendimento = new BigDecimal(String.valueOf(getRendimentoMap.divide(BigDecimal.valueOf(100), MathContext.UNLIMITED).add(BigDecimal.ONE)));
        BigDecimal valorRendido;

        valorRendido = valorASerRendido.multiply(rendimento);

        return ResponseEntity.status(HttpStatus.OK).body(valorRendido.setScale(2, RoundingMode.HALF_UP).toPlainString());
    }
}
