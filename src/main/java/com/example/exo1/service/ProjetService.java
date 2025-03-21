package com.example.exo1.service;

import com.example.exo1.model.Projet;
import com.example.exo1.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetService {

    @Autowired
    private ProjetRepository projetRepository;

    public List<Projet> getAllProjets() {
        return projetRepository.findAll();
    }

    public Optional<Projet> getProjetById(int id) {
        return projetRepository.findById(id);
    }

    public Projet addProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    public Optional<Projet> updateProjet(int id, Projet projetDetails) {
        return projetRepository.findById(id).map(existingProjet -> {
            existingProjet.setNom(projetDetails.getNom());
            existingProjet.setDescription(projetDetails.getDescription());
            existingProjet.setDateCreation(projetDetails.getDateCreation());
            existingProjet.setStatut(projetDetails.getStatut());
            return projetRepository.save(existingProjet);
        });
    }

    public void deleteProjet(int id) {
        projetRepository.deleteById(id);
    }
}
