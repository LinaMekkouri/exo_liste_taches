package com.example.exo1.service;

import com.example.exo1.model.Projet;
import com.example.exo1.model.Tache;
import com.example.exo1.repository.ProjetRepository;
import com.example.exo1.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TacheService {

    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private ProjetRepository projetRepository;

    public Optional<Tache> getTacheById(int id) {
        return tacheRepository.findById(id);
    }

    public List<Tache> getTachesByProjet(long projetId) {
        return tacheRepository.findByProjet(projetId);
    }

    public Page<Tache> getTachesByProjetPaged(long projetId, Pageable pageable) {
        return tacheRepository.findByProjetId(projetId, pageable);
    }

    public Optional<Tache> updateTache(int id, Tache updatedTache) {
        return tacheRepository.findById(id).map(existingTache -> {
            existingTache.setTitre(updatedTache.getTitre());
            existingTache.setDescription(updatedTache.getDescription());
            existingTache.setStatut(updatedTache.getStatut());
            return tacheRepository.save(existingTache);
        });
    }

    public void deleteTache(int id) {
        tacheRepository.deleteById(id);
    }

    public List<Tache> getTachesByStatut(String statut) {
        return tacheRepository.findByStatut(statut);
    }
    public Tache addTacheToProjet(int projetId, Tache tache) {
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé"));
        tache.setProjet(projet);
        tache.setDateCreation(LocalDateTime.now());
        return tacheRepository.save(tache);
    }


    public List<Tache> getTachesByPriorite(String priorite) {
        return tacheRepository.findByPriorite(priorite);
    }
}
