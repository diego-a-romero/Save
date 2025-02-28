package com.financaswhatsapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.financaswhatsapp.entity.Usuario;
import com.financaswhatsapp.repository.UsuarioRepository;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    // Injeção do repository via construtor
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll(); // Retorna todos os usuários do banco
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuarios(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(201).body(novoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> modificarUsuarios(@RequestBody Usuario novoUsuario, @PathVariable UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não localizado"));
        if (novoUsuario.getNome() != null && !novoUsuario.getNome().isEmpty()) {
            usuario.setNome(novoUsuario.getNome());
        }
        if (novoUsuario.getNumeroWhatsapp() != null && !novoUsuario.getNumeroWhatsapp().isEmpty()){
            usuario.setNumeroWhatsapp(novoUsuario.getNumeroWhatsapp());
        }
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuarios(@PathVariable UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não localizado"));
        usuarioRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }
}


