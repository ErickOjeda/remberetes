package com.lembretes.remberetes;

import com.lembretes.remberetes.DTOs.LembreteDTO;
import com.lembretes.remberetes.controllers.LembreteController;
import com.lembretes.remberetes.entitys.Lembrete;
import com.lembretes.remberetes.entitys.Pessoa;
import com.lembretes.remberetes.repositories.LembreteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RemberetesApplicationTests {

	@MockBean
	LembreteRepository repository;

	@Autowired
	private LembreteController controller;

	@BeforeEach
	void injectData(){

		// Primeiro teste
		Optional<Lembrete> lembrete = Optional.of(new Lembrete(1L, "GRAGAS E SHACO", new Pessoa(1L, "Bulla")));
		Mockito.when(repository.findById(1L)).thenReturn(lembrete);

		// Segundo teste
		Pessoa pessoa = new Pessoa(1L, "Bulla");
		List<Lembrete> lembretes = new ArrayList<>();
		lembretes.add(new Lembrete( 1L,"GRAGAS E SHACO", pessoa));
		Mockito.when(repository.findByPessoaNome("Bulla")).thenReturn(lembretes);

		// Terceiro teste
		Lembrete lembrete1 = new Lembrete(1L, "GRAGAS E SHACO", new Pessoa(1L, "Bulla"));
		Mockito.when(repository.save(lembrete1)).thenReturn(lembrete1);

	}

	@Test
	void testGetLembreteById(){
		var response = controller.getLembreteById(1L);
		Long id = response.getBody().get().getId();
		assertEquals(1L, id);
	}

	@Test
	void testGetLembretesByNome(){
		var response = controller.getLembreteByPessoaNome("Bulla");
		String nome = response.getBody().get(0).getPessoa().getNome();

		assertEquals("Bulla", nome);
	}

	@Test
	void testPostLembrete(){
		var response = controller.createPessoa(new LembreteDTO(1L, "GRAGAS E SHACO", new Pessoa(1L, "Bulla")));
		Lembrete lembrete1 = new Lembrete(1L, "GRAGAS E SHACO", new Pessoa(1L, "Bulla"));

		assertEquals(ResponseEntity.ok("Registro feito com sucesso!"), response);
	}


}
