package com.example.exo1.controller;

import com.example.exo1.model.Projet;
import com.example.exo1.model.Tache;
import com.example.exo1.repository.ProjetRepository;
import com.example.exo1.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class TacheController {
    @Autowired
    private TacheRepository tacheRepository;
    @GetMapping("taches/{id}")
    public Tache getTacheById(@PathVariable long id) {
        return TacheRepository.findById(id).orElse(null);
    }
    @GetMapping("/projets/{projetId}/")
    public List<Tache> getTacheByProjet(@PathVariable long projetId) {
        return tacheRepository.findByProjet(projetId);
    }



    @PutMapping("/taches/{id}")
    public ResponseEntity<Tache> updateTache(@PathVariable Long id, @RequestBody Tache tache) {
        return tacheRepository.findById(id).map(existingTache -> {
            existingTache.setTitre(tache.getTitre());
            existingTache.setDescription(tache.getDescription());
            existingTache.setDateCreation(tache.getDateCreation());
            existingTache.setStatut(tache.getStatut());
            Tache savedTache = tacheRepository.save(existingTache); // ✅ Correction du nom de variable
            return ResponseEntity.ok(savedTache); // ✅ Correction du retour
        }).orElseGet(() -> ResponseEntity.notFound().build()); // ✅ Gère le cas où l'ID n'existe pas
    }


    @DeleteMapping("/taches/{id} ")
    public ResponseEntity<Projet> deleteProjet(@PathVariable int id) {
        tacheRepository.deleteById(id);
        return ResponseEntity.ok().build();

    }

}
