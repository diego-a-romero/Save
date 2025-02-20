package com.financaswhatsapp.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "assinaturas")

public class Assinatura {
    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDateTime data_inicio;

    @Column(nullable = true)
    private LocalDateTime expiracao;

    private enum PlanoAssinatura{
        MENSAL, TRIMESTRAL, ANUAL, VIP_MENSAL, VIP_TRIMESTRAL, VIP_ANUAL;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private PlanoAssinatura plano;

    private enum StatusAssinatura{
        ATIVA, CANCELADA, EXPIRADA;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private StatusAssinatura status;

    @PrePersist
    private void setCreationDate(){
        this.data_inicio = LocalDateTime.now();
    }

    @PrePersist
    private void setExpiracao(){
        if (this.expiracao == null) {
            this.expiracao = this.data_inicio.plusMonths(1);
        }
    }
}
