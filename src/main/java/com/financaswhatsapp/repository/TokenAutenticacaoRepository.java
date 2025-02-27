package com.financaswhatsapp.repository;

import com.financaswhatsapp.entity.TokenAutenticacao;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TokenAutenticacaoRepository extends JpaRepository <TokenAutenticacao, UUID> {
}
