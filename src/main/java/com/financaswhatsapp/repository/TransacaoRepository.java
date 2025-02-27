package com.financaswhatsapp.repository;

import com.financaswhatsapp.entity.Transacao;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransacaoRepository extends JpaRepository <Transacao, UUID> {
}
