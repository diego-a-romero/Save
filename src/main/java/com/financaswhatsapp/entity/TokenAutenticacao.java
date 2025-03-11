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
    private Assinatura assinatura;

    @Column(nullable = false, length = 255, unique = true)
    private String chave_api;

    @Column(nullable = false)
    private LocalDateTime data_criacao;

    @Column(nullable = true)
    private LocalDateTime expiracao;

    @Column(nullable = false)
    private Boolean ativo;

    @PrePersist
    private void setDefaults(){
        this.data_criacao = LocalDateTime.now();

        // O token nunca pode expirar depois da assinatura
        if (this.assinatura != null && this.assinatura.getExpiracao() != null) {
            this.expiracao = this.assinatura.getExpiracao();
        } else {
            // Se não houver assinatura válida, o token expira em 1 dia
            this.expiracao = this.data_criacao.plusDays(1);
        }

        this.ativo = true;
    }

    public LocalDateTime getExpiracao() {
        return expiracao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public String getChave_api() {
        return chave_api;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setExpiracao(LocalDateTime expiracao) {
        this.expiracao = expiracao;
    }

    public void setChave_api(String chave_api) {
        this.chave_api = chave_api;
    }
}
