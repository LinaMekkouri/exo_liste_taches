package com.example.exo1.repository;

import com.example.exo1.model.Projet;
import com.example.exo1.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TacheRepository extends JpaRepository <Tache, Integer>{
    List<Tache>findByProjet(Projet projet);

}
