package com.financaswhatsapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.financaswhatsapp.entity.Categoria;
import com.financaswhatsapp.repository.CategoriaRepository;
import java.util.List;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    // Injeção do repository via construtor
    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll(); // Retorna todas as categorias do banco
    }

    @PostMapping
    public ResponseEntity<Categoria> cadastrarCategoria(@RequestBody Categoria categoria) {
        // Verifica se já existe uma categoria com o mesmo nome
        Categoria categoriaExistente = categoriaRepository.findByNome(categoria.getNome()).orElse(null);

        if (categoriaExistente != null) {
            // Se já existe, retorna a categoria encontrada (não cria uma nova)
            return ResponseEntity.ok(categoriaExistente);
        }

        // Se não existir, cria uma nova normalmente
        Categoria novaCategoria = categoriaRepository.save(categoria);
        return ResponseEntity.status(201).body(novaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> modificarCategorias(@RequestBody Categoria novoCategoria, @PathVariable Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não localizada"));
        if (novoCategoria.getNome() != null) {
            categoria.setNome(novoCategoria.getNome());
        }
        categoriaRepository.save(categoria);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategorias(@PathVariable Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não localizada"));
        categoriaRepository.delete(categoria);
        return ResponseEntity.noContent().build();
    }
}


