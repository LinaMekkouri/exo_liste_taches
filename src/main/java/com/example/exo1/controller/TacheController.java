package com.example.exo1.controller;

import com.example.exo1.model.Projet;
import com.example.exo1.model.Tache;
import com.example.exo1.repository.ProjetRepository;
import com.example.exo1.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class TacheController {
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private TacheRepository tacheRepository;
    @GetMapping("/taches/{id}")
    public Tache getTacheById(@PathVariable int id) {
        return tacheRepository.findById(id).orElse(null);
    }
    @GetMapping("/projets/{projetId}/taches")
    public List<Tache> getTacheByProjet(@PathVariable long projetId) {
        return tacheRepository.findByProjet(projetId);
    }
    @PostMapping("/projets/{projetId}/taches")
    public Tache addTask(@RequestBody Tache tache, @PathVariable int projetId){
        Projet projet = projetRepository.findById(projetId).orElse(null);
        if (projet != null) {
            tache.setProjet(projet);
            return tacheRepository.save(tache);
        }
        return null;
    }



    @PutMapping("/taches/{id}")
    public ResponseEntity<Tache> updateTache(@PathVariable int id, @RequestBody Tache tache) {
        return tacheRepository.findById(id).map(existingTache -> {
            existingTache.setTitre(tache.getTitre());
            existingTache.setDescription(tache.getDescription());
            existingTache.setStatut(tache.getStatut());
            Tache savedTache = tacheRepository.save(existingTache);
            return ResponseEntity.ok(savedTache);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/taches/{id}")
    public ResponseEntity<Tache> deleteTache(@PathVariable int id) {
        tacheRepository.deleteById(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/tasks/{statut}")
    public List<Tache> getTasksByStatut(@PathVariable String statut) {
        return tacheRepository.findByStatut(statut);
    }


    @GetMapping("/tasks/{priorite}")
    public List<Tache> getTasksByPriorite(@PathVariable String priorite) {
        return tacheRepository.findByPriorite(priorite);
    }


}





