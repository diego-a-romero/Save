package com.financaswhatsapp.repository;

import com.financaswhatsapp.entity.Usuario;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository <Usuario, UUID> {
}
