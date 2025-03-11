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
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private LocalDateTime dataTransacao;

    @PrePersist
    private void setDefaultDates() {
        this.dataCriacao = LocalDateTime.now();

        // Se o usuário não informar a data da transação, define como "agora"
        if (this.dataTransacao == null) {
            this.dataTransacao = LocalDateTime.now();
        }
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public RecorrenciaPagamento getRecorrencia() {
        return recorrencia;
    }

    public void setRecorrencia(RecorrenciaPagamento recorrencia) {
        this.recorrencia = recorrencia;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
}