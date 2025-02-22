package com.financaswhatsapp.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Objects;


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

    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nome='" + nome + "', numero_whatsapp='" + numero_whatsapp + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
