package com.financaswhatsapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.financaswhatsapp.entity.TokenAutenticacao;
import com.financaswhatsapp.repository.TokenAutenticacaoRepository;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/tokensAutenticacao")
public class TokenAutenticacaoController {

    private final TokenAutenticacaoRepository tokenAutenticacaoRepository;

    // Injeção do repository via construtor
    public TokenAutenticacaoController(TokenAutenticacaoRepository tokenAutenticacaoRepository) {
        this.tokenAutenticacaoRepository = tokenAutenticacaoRepository;
    }

    @GetMapping
    public List<TokenAutenticacao> listarTokenAutenticacaos() {
        return tokenAutenticacaoRepository.findAll(); // Retorna todos os usuários do banco
    }

    @PostMapping
    public ResponseEntity<TokenAutenticacao> cadastrarTokenAutenticacao(@RequestBody TokenAutenticacao tokenAutenticacao) {
        TokenAutenticacao novaTokenAutenticacao = tokenAutenticacaoRepository.save(tokenAutenticacao);
        return ResponseEntity.status(201).body(novaTokenAutenticacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TokenAutenticacao> modificarTokenAutenticacaos(@RequestBody TokenAutenticacao novoTokenAutenticacao, @PathVariable UUID id) {
        TokenAutenticacao tokenAutenticacao = tokenAutenticacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TokenAutenticacao não localizada"));
        if (novoTokenAutenticacao.getPlano() != null) {
            tokenAutenticacao.setPlano(novoTokenAutenticacao.getPlano());
        }
        if (novoTokenAutenticacao.getExpiracao() != null) {
            tokenAutenticacao.setExpiracao(novoTokenAutenticacao.getExpiracao());
        }
        if (novoTokenAutenticacao.getStatus() != null) {
            tokenAutenticacao.setStatus(novoTokenAutenticacao.getStatus());
        }
        tokenAutenticacaoRepository.save(tokenAutenticacao);
        return ResponseEntity.ok(tokenAutenticacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTokenAutenticacaos(@PathVariable UUID id) {
        TokenAutenticacao tokenAutenticacao = tokenAutenticacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TokenAutenticacao não localizada"));
        tokenAutenticacaoRepository.delete(tokenAutenticacao);
        return ResponseEntity.noContent().build();
    }
}


