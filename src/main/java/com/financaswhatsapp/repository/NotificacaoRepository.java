package com.financaswhatsapp.repository;

import com.financaswhatsapp.entity.Notificacao;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificacaoRepository extends JpaRepository <Notificacao, UUID> {
}
