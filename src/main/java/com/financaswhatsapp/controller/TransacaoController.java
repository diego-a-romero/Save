package com.financaswhatsapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.financaswhatsapp.entity.Transacao;
import com.financaswhatsapp.repository.TransacaoRepository;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoRepository transacaoRepository;

    // Injeção do repository via construtor
    public TransacaoController(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @GetMapping
    public List<Transacao> listarTransacoes() {
        return transacaoRepository.findAll(); // Retorna todos os usuários do banco
    }

    @PostMapping
    public ResponseEntity<Transacao> cadastrarTransacoes(@RequestBody Transacao transacao) {
        Transacao novoTransacao = transacaoRepository.save(transacao);
        return ResponseEntity.status(201).body(novoTransacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transacao> modificarTransacoes(@RequestBody Transacao novoTransacao, @PathVariable UUID id) {
        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não localizado"));
        if (novoTransacao.getCategoria() != null) {
            transacao.setCategoria(novoTransacao.getCategoria());
        }
        if (novoTransacao.getDescricao() != null){
            transacao.setDescricao(novoTransacao.getDescricao());
        }
        if (novoTransacao.getValor() != null){
            transacao.setValor(novoTransacao.getValor());
        }
        if (novoTransacao.getQuantidade() != null){
            transacao.setQuantidade(novoTransacao.getQuantidade());
        }
        if (novoTransacao.getRecorrencia() != null){
            transacao.setRecorrencia(novoTransacao.getRecorrencia());
        }
        if (novoTransacao.getDataTransacao() != null){
            transacao.setDataTransacao(novoTransacao.getDataTransacao());
        }
        transacaoRepository.save(transacao);
        return ResponseEntity.ok(transacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTransacoes(@PathVariable UUID id) {
        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não localizado"));
        transacaoRepository.delete(transacao);
        return ResponseEntity.noContent().build();
    }
}


