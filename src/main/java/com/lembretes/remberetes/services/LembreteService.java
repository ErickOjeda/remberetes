package com.lembretes.remberetes.services;

import com.lembretes.remberetes.DTOs.LembreteDTO;
import com.lembretes.remberetes.DTOs.PessoaDTO;
import com.lembretes.remberetes.entitys.Lembrete;
import com.lembretes.remberetes.entitys.Pessoa;
import com.lembretes.remberetes.repositories.LembreteRepository;
import com.lembretes.remberetes.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Repository
public class LembreteService {

    private final LembreteRepository lembreteRepository;

    private final PessoaRepository pessoaRepository;

    @Autowired
    public LembreteService(LembreteRepository lembreteRepository, PessoaRepository pessoaRepository) {
        this.lembreteRepository = lembreteRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional(rollbackFor =  Exception.class)
    public void cadastrar(final LembreteDTO lembreteDTO){

        Assert.isTrue(lembreteDTO.getNome() != null, "Nome não informado");
        Assert.isTrue(lembreteDTO.getPessoa() != null, "Pessoa não informada");

        Lembrete lembrete = new Lembrete();
        lembrete.setNome(lembreteDTO.getNome());
        lembrete.setPessoa(lembreteDTO.getPessoa());

        this.lembreteRepository.save(lembrete);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final LembreteDTO lembreteDTO){

        final Lembrete lembreteBanco = this.lembreteRepository.findById(id).orElse(null);

        Assert.isTrue(lembreteDTO.getId().equals(id), "Id informado difere do Id no corpo da requisição");
        Assert.isTrue(lembreteBanco != null, "Registro não encontrado");
        Assert.isTrue(lembreteDTO.getNome() != null, "Nome não informado");
        Assert.isTrue(lembreteDTO.getPessoa() != null, "Pessoa não informada");

        final Pessoa pessoaBanco = this.pessoaRepository.findById(lembreteDTO.getPessoa().getId()).orElse(null);

        Assert.isTrue(pessoaBanco != null, "Pessoa não encontrada");

        lembreteBanco.setNome(lembreteDTO.getNome());
        lembreteBanco.setPessoa(lembreteDTO.getPessoa());

        this.lembreteRepository.save(lembreteBanco);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(final Long id) {

        final Lembrete lembreteBanco = this.lembreteRepository.findById(id).orElse(null);

        Assert.isTrue(lembreteBanco != null, "Registro não encontrado");

        this.lembreteRepository.delete(lembreteBanco);

    }
}
