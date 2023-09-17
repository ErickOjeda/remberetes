package com.lembretes.remberetes.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name="pessoas", schema="public")
public class Pessoa {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter @Setter
    @Column(name = "nome", nullable = false)
    private String nome;


    public Pessoa(Long id, String nome) {
        this.id  = id;
        this.nome = nome;
    }

    public Pessoa (){

    }
}
