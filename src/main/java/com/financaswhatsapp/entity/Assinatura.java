package com.financaswhatsapp.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import java.time.LocalDateTime;
import java.util.UUID;
import com.financaswhatsapp.enums.PlanoAssinatura;
import com.financaswhatsapp.enums.StatusAssinatura;


@Entity
@Table(name = "assinaturas")

public class Assinatura {
    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDateTime data_inicio;

    @Column
    private LocalDateTime expiracao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private PlanoAssinatura plano;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private StatusAssinatura status;

    @PrePersist
    private void setCreationAndExpiration(){
        this.data_inicio = LocalDateTime.now();

        if (this.expiracao == null) {
            this.expiracao = this.data_inicio.plusMonths(1);
        }
    }

    public LocalDateTime getExpiracao() {
        return expiracao;
    }
}
