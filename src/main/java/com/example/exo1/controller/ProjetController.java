package com.example.exo1.controller;

import com.example.exo1.model.Projet;
import com.example.exo1.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projets")
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @GetMapping
    public List<Projet> getAllProjets() {
        return projetService.getAllProjets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projet> getProjetById(@PathVariable int id) {
        return projetService.getProjetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Projet addProjet(@RequestBody Projet projet) {
        return projetService.addProjet(projet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projet> updateProjet(@PathVariable int id, @RequestBody Projet projet) {
        return projetService.updateProjet(id, projet)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjet(@PathVariable int id) {
        projetService.deleteProjet(id);
        return ResponseEntity.ok().build();
    }
}
