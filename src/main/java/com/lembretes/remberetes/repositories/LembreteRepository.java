package com.lembretes.remberetes.repositories;

import com.lembretes.remberetes.entitys.Lembrete;
import com.lembretes.remberetes.entitys.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LembreteRepository extends JpaRepository<Lembrete, Long> {

    @Query("from Lembrete where pessoa = :pessoa")
    public List<Lembrete> findByPessoa(@Param("pessoa") Pessoa pessoa);

    @Query("from Lembrete where pessoa.nome = :nome")
    public List<Lembrete> findByPessoaNome(@Param("nome") String nome);
}
