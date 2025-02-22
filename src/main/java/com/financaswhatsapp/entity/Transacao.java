package com.financaswhatsapp.entity;

import com.financaswhatsapp.enums.RecorrenciaPagamento;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "transacoes")

public class Transacao {
    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private RecorrenciaPagamento recorrencia;

    @Column(nullable = false)
    private LocalDateTime data_criacao;

    @Column(nullable = false)
    private LocalDateTime data;

    @PrePersist
    private void setDefaultDates() {
        this.data_criacao = LocalDateTime.now();

        // Se o usuário não informar a data da transação, define como "agora"
        if (this.data == null) {
            this.data = LocalDateTime.now();
        }
    }
}