package com.lembretes.remberetes.controllers;

import com.lembretes.remberetes.DTOs.LembreteDTO;
import com.lembretes.remberetes.DTOs.PessoaDTO;
import com.lembretes.remberetes.entitys.Lembrete;
import com.lembretes.remberetes.repositories.LembreteRepository;
import com.lembretes.remberetes.services.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/lembretes")
public class LembreteController {

    @Autowired
    private final LembreteRepository lembreteRepository;

    @Autowired
    private final LembreteService lembreteService;

    public LembreteController (LembreteRepository lembreteRepository, LembreteService lembreteService) {
        this.lembreteRepository = lembreteRepository;
        this.lembreteService = lembreteService;
    }

    @GetMapping
    public ResponseEntity<List<Lembrete>> getAllLembretes () {
        return ResponseEntity.ok(lembreteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Lembrete>> getLembreteById (@PathVariable Long id) {
        return ResponseEntity.ok(lembreteRepository.findById(id));
    }

    @GetMapping("/buscaPorNome/{nome}")
    public ResponseEntity<List<Lembrete>> getLembreteByPessoaNome (@PathVariable String nome) {
        return ResponseEntity.ok(lembreteRepository.findByPessoaNome(nome));
    }

    @PostMapping
    public ResponseEntity<String> createPessoa(@RequestBody LembreteDTO lembreteDTO) {
        try
        {
            lembreteService.cadastrar(lembreteDTO);
            return ResponseEntity.ok("Registro feito com sucesso!");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePessoa(@PathVariable Long id, @RequestBody LembreteDTO lembreteDTO) {
        try
        {
            lembreteService.editar(id, lembreteDTO);
            return ResponseEntity.ok("Registro atualizado com sucesso!");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePessoa(@PathVariable Long id) {
        try {
            this.lembreteService.deletar(id);
            return ResponseEntity.ok().body("Registro deletado com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        }
    }
}