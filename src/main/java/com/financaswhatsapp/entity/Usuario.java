package com.financaswhatsapp.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "usuarios")

public class Usuario {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(unique = true, length = 15)
    private String numero_whatsapp;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDateTime data_criacao;

    @PrePersist
    private void setCreationDate(){
        this.data_criacao = LocalDateTime.now();
    }
}
