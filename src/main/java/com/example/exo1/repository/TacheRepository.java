package com.example.exo1.repository;

import com.example.exo1.model.Projet;
import com.example.exo1.model.Tache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TacheRepository extends JpaRepository <Tache, Integer>{
    List<Tache>findByProjet(long projectId);
    List<Tache> findByStatut(String statut);
    List<Tache> findByPriorite(String priorite);
    Page<Tache> findByProjetId(long projetId, Pageable pageable);


}
