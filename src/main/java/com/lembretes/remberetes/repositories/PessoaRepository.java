package com.lembretes.remberetes.repositories;

import com.lembretes.remberetes.entitys.Lembrete;
import com.lembretes.remberetes.entitys.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
