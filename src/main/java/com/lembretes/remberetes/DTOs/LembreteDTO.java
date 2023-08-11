package com.lembretes.remberetes.DTOs;

import com.lembretes.remberetes.entitys.Pessoa;
import lombok.Data;
@Data
public class LembreteDTO {

    private Long id;

    private Pessoa pessoa;

    private String nome;

}
