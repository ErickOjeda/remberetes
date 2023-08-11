package com.lembretes.remberetes.controllers;

import com.lembretes.remberetes.DTOs.PessoaDTO;
import com.lembretes.remberetes.entitys.Pessoa;
import com.lembretes.remberetes.repositories.PessoaRepository;
import com.lembretes.remberetes.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private final PessoaRepository pessoaRepository;

    @Autowired
    private final PessoaService pessoaService;

    public PessoaController(PessoaRepository pessoaRepository, PessoaService pessoaService) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas () {
        return ResponseEntity.ok(pessoaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPessoaById (@PathVariable Long id) {
        try{
            return ResponseEntity.ok(this.pessoaRepository.findById(id));
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> createPessoa(@RequestBody PessoaDTO pessoa) {
        try
        {
            pessoaService.cadastrar(pessoa);
            return ResponseEntity.ok("Registro feito com sucesso!");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoa) {
        try
        {
            pessoaService.editar(id, pessoa);
            return ResponseEntity.ok("Registro atualizado com sucesso!");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePessoa(@PathVariable Long id) {
        try {
            this.pessoaService.deletar(id);
            return ResponseEntity.ok().body("Registro deletado com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        }
    }
}