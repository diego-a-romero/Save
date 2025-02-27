package com.financaswhatsapp.repository;

import com.financaswhatsapp.entity.Categoria;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaRepository extends JpaRepository <Categoria, UUID> {
}
