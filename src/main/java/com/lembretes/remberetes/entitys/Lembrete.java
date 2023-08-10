package com.lembretes.remberetes.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

public class Lembrete {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter @Setter
    @Column(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;

    @Getter @Setter
    @Column(name = "", nullable = false)
    private String nome;

    @Getter @Setter
    private String desc;


}
