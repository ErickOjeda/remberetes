package com.lembretes.remberetes.controllers;

import com.lembretes.remberetes.entitys.Lembrete;
import com.lembretes.remberetes.repositories.LembreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/lembretes")
public class LembreteController {

    private final LembreteRepository lembreteRepository;

    @Autowired
    public LembreteController(LembreteRepository lembreteRepository) {
        this.lembreteRepository = lembreteRepository;
    }

    @GetMapping
    public List<Lembrete> getAllLembretes() {
        return lembreteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Lembrete> getLembreteById(@PathVariable Long id) {
        return lembreteRepository.findById(id);
    }

    @PostMapping
    public Lembrete createLembrete(@RequestBody Lembrete lembrete) {
        return lembreteRepository.save(lembrete);
    }

    @PutMapping("/{id}")
    public Lembrete updateLembrete(@PathVariable Long id, @RequestBody Lembrete updatedLembrete) {
        if (lembreteRepository.existsById(id)) {
            updatedLembrete.setId(id);
            return lembreteRepository.save(updatedLembrete);
        } else {
            return null; // Return appropriate response or throw an exception
        }
    }

    @DeleteMapping("/{id}")
    public void deleteLembrete(@PathVariable Long id) {
        lembreteRepository.deleteById(id);
    }
}