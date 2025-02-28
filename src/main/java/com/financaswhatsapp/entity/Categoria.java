package com.financaswhatsapp.entity;
import jakarta.persistence.*;



@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID como autoincremento
    private Integer id;

    @Column(unique = true, nullable = false, length = 50) // Nome deve ser único
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
