package com.financaswhatsapp.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "tokens_autenticacao")

public class TokenAutenticacao {
    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "assinatura_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 255, unique = true)
    private String chave_api;

    @Column(nullable = false)
    private LocalDateTime data_criacao;

    @Column(nullable = true)
    private LocalDateTime expiracao;

    @Column(nullable = false)
    private Boolean ativo;

    @PrePersist
    private void setCreationDate(){
        this.data_criacao = LocalDateTime.now();
    }

    private void setExpiracao(){
        this.expiracao = LocalDateTime.now();
    }
}
