package com.example.exo1.controller;

import com.example.exo1.model.Tache;
import com.example.exo1.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TacheController {

    @Autowired
    private TacheService tacheService;

    @GetMapping("/taches/{id}")
    public ResponseEntity<Tache> getTacheById(@PathVariable int id) {
        return tacheService.getTacheById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/projets/{projetId}/taches")
    public ResponseEntity<?> getTachesByProjet(
            @PathVariable long projetId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort) {

        if (page != null && size != null) {
            Pageable pageable = (sort != null)
                    ? PageRequest.of(page, size, Sort.by(sort))
                    : PageRequest.of(page, size);
            Page<Tache> pagedTaches = tacheService.getTachesByProjetPaged(projetId, pageable);
            return ResponseEntity.ok(pagedTaches);
        } else {
            List<Tache> taches = tacheService.getTachesByProjet(projetId);
            return ResponseEntity.ok(taches);
        }
    }

    @PutMapping("/taches/{id}")
    public ResponseEntity<Tache> updateTache(@PathVariable int id, @RequestBody Tache tache) {
        return tacheService.updateTache(id, tache)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/taches/{id}")
    public ResponseEntity<Void> deleteTache(@PathVariable int id) {
        tacheService.deleteTache(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tasks/statut/{statut}")
    public List<Tache> getTachesByStatut(@PathVariable String statut) {
        return tacheService.getTachesByStatut(statut);
    }

    @GetMapping("/tasks/priorite/{priorite}")
    public List<Tache> getTachesByPriorite(@PathVariable String priorite) {
        return tacheService.getTachesByPriorite(priorite);
    }
}
