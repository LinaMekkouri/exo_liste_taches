package com.example.exo1.controller;

import com.example.exo1.model.Projet;
import com.example.exo1.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api")

public class ProjetController {
    @Autowired
    private ProjetRepository projetRepository;

    @GetMapping("/projets")
    public List<Projet> getAllProjets() {
        return projetRepository.findAll();
    }

    @GetMapping("projets/{id}")
    public Projet getProjetById(@PathVariable int id) {
        return projetRepository.findById(id).orElse(null);
    }

    @PostMapping("/projets")
    public Projet addProjet(@RequestBody Projet projet) {
        return projetRepository.save(projet);
    }

    @PutMapping("/projets/{id}")
    public ResponseEntity<Projet> updateProjet(@PathVariable int id, @RequestBody Projet projet) {
        return projetRepository.findById(id).map(existingProjet -> {
            existingProjet.setNom(projet.getNom());
            existingProjet.setDescription(projet.getDescription());
            existingProjet.setDateCreation(projet.getDateCreation());
            existingProjet.setStatut(projet.getStatut());
            Projet savedProjet = projetRepository.save(existingProjet);
            return ResponseEntity.ok(savedProjet);
        }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/projets/{id}")
    public ResponseEntity<Projet> deleteProjet(@PathVariable int id) {
        projetRepository.deleteById(id);
        return ResponseEntity.ok().build();

    }

}
