package com.lembretes.remberetes.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="lembretes", schema="public")
public class Lembrete {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Getter @Setter
    @Column(name = "nome", nullable = false)
    private String nome;


    public Lembrete(long id, String nome, Pessoa pessoa) {
        this.id = id;
        this.nome = nome;
        this.pessoa = pessoa;

    }

    public Lembrete(){

    }
}
