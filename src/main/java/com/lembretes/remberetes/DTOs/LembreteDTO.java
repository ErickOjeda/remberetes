package com.lembretes.remberetes.DTOs;

import com.lembretes.remberetes.entitys.Pessoa;
import lombok.Data;
@Data
public class LembreteDTO {

    private Long id;

    private Pessoa pessoa;

    private String nome;

    public LembreteDTO(Long id, String nome, Pessoa pessoa) {
        this.id = id;
        this.pessoa = pessoa;
        this.nome = nome;
    }
}
