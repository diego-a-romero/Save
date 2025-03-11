package com.financaswhatsapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.financaswhatsapp.entity.Assinatura;
import com.financaswhatsapp.repository.AssinaturaRepository;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/assinaturas")
public class AssinaturaController {

    private final AssinaturaRepository assinaturaRepository;

    // Injeção do repository via construtor
    public AssinaturaController(AssinaturaRepository assinaturaRepository) {
        this.assinaturaRepository = assinaturaRepository;
    }

    @GetMapping
    public List<Assinatura> listarAssinaturas() {
        return assinaturaRepository.findAll(); // Retorna todos os usuários do banco
    }

    @PostMapping
    public ResponseEntity<Assinatura> cadastrarAssinaturas(@RequestBody Assinatura assinatura) {
        Assinatura novaAssinatura = assinaturaRepository.save(assinatura);
        return ResponseEntity.status(201).body(novaAssinatura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assinatura> modificarAssinaturas(@RequestBody Assinatura novoAssinatura, @PathVariable UUID id) {
        Assinatura assinatura = assinaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assinatura não localizada"));
        if (novoAssinatura.getPlano() != null) {
            assinatura.setPlano(novoAssinatura.getPlano());
        }
        if (novoAssinatura.getExpiracao() != null) {
            assinatura.setExpiracao(novoAssinatura.getExpiracao());
        }
        if (novoAssinatura.getStatus() != null) {
            assinatura.setStatus(novoAssinatura.getStatus());
        }
        assinaturaRepository.save(assinatura);
        return ResponseEntity.ok(assinatura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAssinaturas(@PathVariable UUID id) {
        Assinatura assinatura = assinaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assinatura não localizada"));
        assinaturaRepository.delete(assinatura);
        return ResponseEntity.noContent().build();
    }
}


