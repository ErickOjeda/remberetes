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
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final LembreteRepository lembreteRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository, LembreteRepository lembreteRepository) {
        this.pessoaRepository = pessoaRepository;
        this.lembreteRepository = lembreteRepository;
    }

    @Transactional(rollbackFor =  Exception.class)
    public void cadastrar(final PessoaDTO newPessoaDTO){

        Assert.isTrue(newPessoaDTO.getNome() != null, "Nome não informado");

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(newPessoaDTO.getNome());

        this.pessoaRepository.save(pessoa);

    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final PessoaDTO pessoaDTO){

        final Pessoa pessoaBanco = this.pessoaRepository.findById(id).orElse(null);
        Assert.isTrue(pessoaDTO.getId().equals(id), "Id informado difere do Id no corpo da requisição");
        Assert.isTrue(pessoaBanco != null, "Registro não encontrado");
        Assert.isTrue(pessoaDTO.getNome() != null, "Nome não informado");

        pessoaBanco.setNome(pessoaDTO.getNome());

        this.pessoaRepository.save(pessoaBanco);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(final Long id) {

        final Pessoa pessoaBanco = this.pessoaRepository.findById(id).orElse(null);

        Assert.isTrue(pessoaBanco != null, "Registro não encontrado");

        final List<Lembrete> lembretes = this.lembreteRepository.findByPessoa(pessoaBanco);

        Assert.isTrue(lembretes.isEmpty(), "Pessoa possui lembretes, não pode ser deletada!");

        this.pessoaRepository.delete(pessoaBanco);

    }

}
