package com.financaswhatsapp.controller;

import org.springframework.web.bind.annotation.*;
import com.financaswhatsapp.entity.Usuario;
import com.financaswhatsapp.repository.UsuarioRepository;
import java.util.List;


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
    public Usuario cadastrarUsuarios(@RequestBody Usuario usuario) {

        return usuarioRepository.save(usuario);
    }
}


