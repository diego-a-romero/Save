package com.financaswhatsapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.financaswhatsapp.entity.Notificacao;
import com.financaswhatsapp.repository.NotificacaoRepository;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    private final NotificacaoRepository notificacaoRepository;

    // Injeção do repository via construtor
    public NotificacaoController(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    @GetMapping
    public List<Notificacao> listarNotificacoes() {
        return notificacaoRepository.findAll(); // Retorna todos os usuários do banco
    }

    @PostMapping
    public ResponseEntity<Notificacao> cadastrarNotificacoes(@RequestBody Notificacao notificacao) {
        Notificacao novaNotificacao = notificacaoRepository.save(notificacao);
        return ResponseEntity.status(201).body(novaNotificacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacao> modificarNotificacoes(@RequestBody Notificacao novoNotificacao, @PathVariable UUID id) {
        Notificacao notificacao = notificacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificacao não localizada"));
        if (novoNotificacao.getMensagem() != null) {
            notificacao.setMensagem(novoNotificacao.getMensagem());
        }
        notificacaoRepository.save(notificacao);
        return ResponseEntity.ok(notificacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNotificoes(@PathVariable UUID id) {
        Notificacao notificacao = notificacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificacao não localizada"));
        notificacaoRepository.delete(notificacao);
        return ResponseEntity.noContent().build();
    }
}


